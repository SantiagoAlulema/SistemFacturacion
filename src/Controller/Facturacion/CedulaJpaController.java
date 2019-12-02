/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Facturacion;

import Controller.Facturacion.exceptions.NonexistentEntityException;
import Controller.Facturacion.exceptions.PreexistingEntityException;
import DAO.Facturacion.Cedula;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DAO.Facturacion.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Santiago
 */
public class CedulaJpaController implements Serializable {

    public CedulaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cedula cedula) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idCliente = cedula.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                cedula.setIdCliente(idCliente);
            }
            em.persist(cedula);
            if (idCliente != null) {
                idCliente.getCedulaList().add(cedula);
                idCliente = em.merge(idCliente);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCedula(cedula.getCedula()) != null) {
                throw new PreexistingEntityException("Cedula " + cedula + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cedula cedula) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cedula persistentCedula = em.find(Cedula.class, cedula.getCedula());
            Cliente idClienteOld = persistentCedula.getIdCliente();
            Cliente idClienteNew = cedula.getIdCliente();
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                cedula.setIdCliente(idClienteNew);
            }
            cedula = em.merge(cedula);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getCedulaList().remove(cedula);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getCedulaList().add(cedula);
                idClienteNew = em.merge(idClienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cedula.getCedula();
                if (findCedula(id) == null) {
                    throw new NonexistentEntityException("The cedula with id " + id + " no longer exists.");
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
            Cedula cedula;
            try {
                cedula = em.getReference(Cedula.class, id);
                cedula.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cedula with id " + id + " no longer exists.", enfe);
            }
            Cliente idCliente = cedula.getIdCliente();
            if (idCliente != null) {
                idCliente.getCedulaList().remove(cedula);
                idCliente = em.merge(idCliente);
            }
            em.remove(cedula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cedula> findCedulaEntities() {
        return findCedulaEntities(true, -1, -1);
    }

    public List<Cedula> findCedulaEntities(int maxResults, int firstResult) {
        return findCedulaEntities(false, maxResults, firstResult);
    }

    private List<Cedula> findCedulaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cedula.class));
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

    public Cedula findCedula(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cedula.class, id);
        } finally {
            em.close();
        }
    }

    public int getCedulaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cedula> rt = cq.from(Cedula.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
