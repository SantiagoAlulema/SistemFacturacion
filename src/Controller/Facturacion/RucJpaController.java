/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Facturacion;

import Controller.Facturacion.exceptions.NonexistentEntityException;
import Controller.Facturacion.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DAO.Facturacion.Cliente;
import DAO.Facturacion.Ruc;
import DAO.Facturacion.Tiporuc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Santiago
 */
public class RucJpaController implements Serializable {

    public RucJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ruc ruc) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idCliente = ruc.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                ruc.setIdCliente(idCliente);
            }
            Tiporuc idTipoRuc = ruc.getIdTipoRuc();
            if (idTipoRuc != null) {
                idTipoRuc = em.getReference(idTipoRuc.getClass(), idTipoRuc.getIdTipoRuc());
                ruc.setIdTipoRuc(idTipoRuc);
            }
            em.persist(ruc);
            if (idCliente != null) {
                idCliente.getRucList().add(ruc);
                idCliente = em.merge(idCliente);
            }
            if (idTipoRuc != null) {
                idTipoRuc.getRucList().add(ruc);
                idTipoRuc = em.merge(idTipoRuc);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRuc(ruc.getRuc()) != null) {
                throw new PreexistingEntityException("Ruc " + ruc + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ruc ruc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ruc persistentRuc = em.find(Ruc.class, ruc.getRuc());
            Cliente idClienteOld = persistentRuc.getIdCliente();
            Cliente idClienteNew = ruc.getIdCliente();
            Tiporuc idTipoRucOld = persistentRuc.getIdTipoRuc();
            Tiporuc idTipoRucNew = ruc.getIdTipoRuc();
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                ruc.setIdCliente(idClienteNew);
            }
            if (idTipoRucNew != null) {
                idTipoRucNew = em.getReference(idTipoRucNew.getClass(), idTipoRucNew.getIdTipoRuc());
                ruc.setIdTipoRuc(idTipoRucNew);
            }
            ruc = em.merge(ruc);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getRucList().remove(ruc);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getRucList().add(ruc);
                idClienteNew = em.merge(idClienteNew);
            }
            if (idTipoRucOld != null && !idTipoRucOld.equals(idTipoRucNew)) {
                idTipoRucOld.getRucList().remove(ruc);
                idTipoRucOld = em.merge(idTipoRucOld);
            }
            if (idTipoRucNew != null && !idTipoRucNew.equals(idTipoRucOld)) {
                idTipoRucNew.getRucList().add(ruc);
                idTipoRucNew = em.merge(idTipoRucNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = ruc.getRuc();
                if (findRuc(id) == null) {
                    throw new NonexistentEntityException("The ruc with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ruc ruc;
            try {
                ruc = em.getReference(Ruc.class, id);
                ruc.getRuc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ruc with id " + id + " no longer exists.", enfe);
            }
            Cliente idCliente = ruc.getIdCliente();
            if (idCliente != null) {
                idCliente.getRucList().remove(ruc);
                idCliente = em.merge(idCliente);
            }
            Tiporuc idTipoRuc = ruc.getIdTipoRuc();
            if (idTipoRuc != null) {
                idTipoRuc.getRucList().remove(ruc);
                idTipoRuc = em.merge(idTipoRuc);
            }
            em.remove(ruc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ruc> findRucEntities() {
        return findRucEntities(true, -1, -1);
    }

    public List<Ruc> findRucEntities(int maxResults, int firstResult) {
        return findRucEntities(false, maxResults, firstResult);
    }

    private List<Ruc> findRucEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ruc.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Ruc findRuc(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ruc.class, id);
        } finally {
            em.close();
        }
    }

    public int getRucCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ruc> rt = cq.from(Ruc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
