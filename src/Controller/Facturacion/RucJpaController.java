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
import DAO.Facturacion.Pcliente;
import DAO.Facturacion.Ruc;
import DAO.Facturacion.Tiporuc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Santiago
 */
public class RucJpaController implements Serializable {

    public RucJpaController( ) {
        this.emf = Persistence.createEntityManagerFactory("SistemaFacturacionPU");
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
            Pcliente idPCliente = ruc.getIdPCliente();
            if (idPCliente != null) {
                idPCliente = em.getReference(idPCliente.getClass(), idPCliente.getIdPCliente());
                ruc.setIdPCliente(idPCliente);
            }
            Tiporuc idTipoRuc = ruc.getIdTipoRuc();
            if (idTipoRuc != null) {
                idTipoRuc = em.getReference(idTipoRuc.getClass(), idTipoRuc.getIdTipoRuc());
                ruc.setIdTipoRuc(idTipoRuc);
            }
            em.persist(ruc);
            if (idPCliente != null) {
                idPCliente.getRucList().add(ruc);
                idPCliente = em.merge(idPCliente);
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
            Pcliente idPClienteOld = persistentRuc.getIdPCliente();
            Pcliente idPClienteNew = ruc.getIdPCliente();
            Tiporuc idTipoRucOld = persistentRuc.getIdTipoRuc();
            Tiporuc idTipoRucNew = ruc.getIdTipoRuc();
            if (idPClienteNew != null) {
                idPClienteNew = em.getReference(idPClienteNew.getClass(), idPClienteNew.getIdPCliente());
                ruc.setIdPCliente(idPClienteNew);
            }
            if (idTipoRucNew != null) {
                idTipoRucNew = em.getReference(idTipoRucNew.getClass(), idTipoRucNew.getIdTipoRuc());
                ruc.setIdTipoRuc(idTipoRucNew);
            }
            ruc = em.merge(ruc);
            if (idPClienteOld != null && !idPClienteOld.equals(idPClienteNew)) {
                idPClienteOld.getRucList().remove(ruc);
                idPClienteOld = em.merge(idPClienteOld);
            }
            if (idPClienteNew != null && !idPClienteNew.equals(idPClienteOld)) {
                idPClienteNew.getRucList().add(ruc);
                idPClienteNew = em.merge(idPClienteNew);
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
            Pcliente idPCliente = ruc.getIdPCliente();
            if (idPCliente != null) {
                idPCliente.getRucList().remove(ruc);
                idPCliente = em.merge(idPCliente);
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
