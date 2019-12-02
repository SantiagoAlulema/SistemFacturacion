/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Facturacion;

import Controller.Facturacion.exceptions.NonexistentEntityException;
import Controller.Facturacion.exceptions.PreexistingEntityException;
import DAO.Facturacion.Devolucion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DAO.Facturacion.Documentopago;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Santiago
 */
public class DevolucionJpaController implements Serializable {

    public DevolucionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Devolucion devolucion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documentopago idFactura = devolucion.getIdFactura();
            if (idFactura != null) {
                idFactura = em.getReference(idFactura.getClass(), idFactura.getIdDocumento());
                devolucion.setIdFactura(idFactura);
            }
            em.persist(devolucion);
            if (idFactura != null) {
                idFactura.getDevolucionList().add(devolucion);
                idFactura = em.merge(idFactura);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDevolucion(devolucion.getIdDevolucion()) != null) {
                throw new PreexistingEntityException("Devolucion " + devolucion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Devolucion devolucion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Devolucion persistentDevolucion = em.find(Devolucion.class, devolucion.getIdDevolucion());
            Documentopago idFacturaOld = persistentDevolucion.getIdFactura();
            Documentopago idFacturaNew = devolucion.getIdFactura();
            if (idFacturaNew != null) {
                idFacturaNew = em.getReference(idFacturaNew.getClass(), idFacturaNew.getIdDocumento());
                devolucion.setIdFactura(idFacturaNew);
            }
            devolucion = em.merge(devolucion);
            if (idFacturaOld != null && !idFacturaOld.equals(idFacturaNew)) {
                idFacturaOld.getDevolucionList().remove(devolucion);
                idFacturaOld = em.merge(idFacturaOld);
            }
            if (idFacturaNew != null && !idFacturaNew.equals(idFacturaOld)) {
                idFacturaNew.getDevolucionList().add(devolucion);
                idFacturaNew = em.merge(idFacturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = devolucion.getIdDevolucion();
                if (findDevolucion(id) == null) {
                    throw new NonexistentEntityException("The devolucion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Devolucion devolucion;
            try {
                devolucion = em.getReference(Devolucion.class, id);
                devolucion.getIdDevolucion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The devolucion with id " + id + " no longer exists.", enfe);
            }
            Documentopago idFactura = devolucion.getIdFactura();
            if (idFactura != null) {
                idFactura.getDevolucionList().remove(devolucion);
                idFactura = em.merge(idFactura);
            }
            em.remove(devolucion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Devolucion> findDevolucionEntities() {
        return findDevolucionEntities(true, -1, -1);
    }

    public List<Devolucion> findDevolucionEntities(int maxResults, int firstResult) {
        return findDevolucionEntities(false, maxResults, firstResult);
    }

    private List<Devolucion> findDevolucionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Devolucion.class));
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

    public Devolucion findDevolucion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Devolucion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDevolucionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Devolucion> rt = cq.from(Devolucion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
