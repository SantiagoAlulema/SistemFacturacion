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
import DAO.Facturacion.Pcliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Santiago
 */
public class CedulaJpaController implements Serializable {

    public CedulaJpaController( ) {
        this.emf = Persistence.createEntityManagerFactory("SistemaFacturacionPU");
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
            Pcliente idPCliente = cedula.getIdPCliente();
            if (idPCliente != null) {
                idPCliente = em.getReference(idPCliente.getClass(), idPCliente.getIdPCliente());
                cedula.setIdPCliente(idPCliente);
            }
            em.persist(cedula);
            if (idPCliente != null) {
                idPCliente.getCedulaList().add(cedula);
                idPCliente = em.merge(idPCliente);
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
            Pcliente idPClienteOld = persistentCedula.getIdPCliente();
            Pcliente idPClienteNew = cedula.getIdPCliente();
            if (idPClienteNew != null) {
                idPClienteNew = em.getReference(idPClienteNew.getClass(), idPClienteNew.getIdPCliente());
                cedula.setIdPCliente(idPClienteNew);
            }
            cedula = em.merge(cedula);
            if (idPClienteOld != null && !idPClienteOld.equals(idPClienteNew)) {
                idPClienteOld.getCedulaList().remove(cedula);
                idPClienteOld = em.merge(idPClienteOld);
            }
            if (idPClienteNew != null && !idPClienteNew.equals(idPClienteOld)) {
                idPClienteNew.getCedulaList().add(cedula);
                idPClienteNew = em.merge(idPClienteNew);
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
            Pcliente idPCliente = cedula.getIdPCliente();
            if (idPCliente != null) {
                idPCliente.getCedulaList().remove(cedula);
                idPCliente = em.merge(idPCliente);
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
