/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Facturacion;

import Controller.Facturacion.exceptions.NonexistentEntityException;
import DAO.Facturacion.Consumidorfinal;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DAO.Facturacion.Documentopago;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sanch
 */
public class ConsumidorfinalJpaController implements Serializable {

    public ConsumidorfinalJpaController() {
        this.emf = this.emf = Persistence.createEntityManagerFactory("SistemaFacturacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Consumidorfinal consumidorfinal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documentopago idFactura = consumidorfinal.getIdFactura();
            if (idFactura != null) {
                idFactura = em.getReference(idFactura.getClass(), idFactura.getIdFactura());
                consumidorfinal.setIdFactura(idFactura);
            }
            em.persist(consumidorfinal);
            if (idFactura != null) {
                idFactura.getConsumidorfinalList().add(consumidorfinal);
                idFactura = em.merge(idFactura);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Consumidorfinal consumidorfinal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consumidorfinal persistentConsumidorfinal = em.find(Consumidorfinal.class, consumidorfinal.getIdConsumidorFinal());
            Documentopago idFacturaOld = persistentConsumidorfinal.getIdFactura();
            Documentopago idFacturaNew = consumidorfinal.getIdFactura();
            if (idFacturaNew != null) {
                idFacturaNew = em.getReference(idFacturaNew.getClass(), idFacturaNew.getIdFactura());
                consumidorfinal.setIdFactura(idFacturaNew);
            }
            consumidorfinal = em.merge(consumidorfinal);
            if (idFacturaOld != null && !idFacturaOld.equals(idFacturaNew)) {
                idFacturaOld.getConsumidorfinalList().remove(consumidorfinal);
                idFacturaOld = em.merge(idFacturaOld);
            }
            if (idFacturaNew != null && !idFacturaNew.equals(idFacturaOld)) {
                idFacturaNew.getConsumidorfinalList().add(consumidorfinal);
                idFacturaNew = em.merge(idFacturaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = consumidorfinal.getIdConsumidorFinal();
                if (findConsumidorfinal(id) == null) {
                    throw new NonexistentEntityException("The consumidorfinal with id " + id + " no longer exists.");
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
            Consumidorfinal consumidorfinal;
            try {
                consumidorfinal = em.getReference(Consumidorfinal.class, id);
                consumidorfinal.getIdConsumidorFinal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The consumidorfinal with id " + id + " no longer exists.", enfe);
            }
            Documentopago idFactura = consumidorfinal.getIdFactura();
            if (idFactura != null) {
                idFactura.getConsumidorfinalList().remove(consumidorfinal);
                idFactura = em.merge(idFactura);
            }
            em.remove(consumidorfinal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Consumidorfinal> findConsumidorfinalEntities() {
        return findConsumidorfinalEntities(true, -1, -1);
    }

    public List<Consumidorfinal> findConsumidorfinalEntities(int maxResults, int firstResult) {
        return findConsumidorfinalEntities(false, maxResults, firstResult);
    }

    private List<Consumidorfinal> findConsumidorfinalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Consumidorfinal.class));
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

    public Consumidorfinal findConsumidorfinal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Consumidorfinal.class, id);
        } finally {
            em.close();
        }
    }

    public int getConsumidorfinalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Consumidorfinal> rt = cq.from(Consumidorfinal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
