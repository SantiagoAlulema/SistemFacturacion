/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Facturacion;

import Controller.Facturacion.exceptions.IllegalOrphanException;
import Controller.Facturacion.exceptions.NonexistentEntityException;
import Controller.Facturacion.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DAO.Facturacion.Ruc;
import DAO.Facturacion.Tiporuc;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Santiago
 */
public class TiporucJpaController implements Serializable {

    public TiporucJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tiporuc tiporuc) throws PreexistingEntityException, Exception {
        if (tiporuc.getRucList() == null) {
            tiporuc.setRucList(new ArrayList<Ruc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ruc> attachedRucList = new ArrayList<Ruc>();
            for (Ruc rucListRucToAttach : tiporuc.getRucList()) {
                rucListRucToAttach = em.getReference(rucListRucToAttach.getClass(), rucListRucToAttach.getRuc());
                attachedRucList.add(rucListRucToAttach);
            }
            tiporuc.setRucList(attachedRucList);
            em.persist(tiporuc);
            for (Ruc rucListRuc : tiporuc.getRucList()) {
                Tiporuc oldIdTipoRucOfRucListRuc = rucListRuc.getIdTipoRuc();
                rucListRuc.setIdTipoRuc(tiporuc);
                rucListRuc = em.merge(rucListRuc);
                if (oldIdTipoRucOfRucListRuc != null) {
                    oldIdTipoRucOfRucListRuc.getRucList().remove(rucListRuc);
                    oldIdTipoRucOfRucListRuc = em.merge(oldIdTipoRucOfRucListRuc);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTiporuc(tiporuc.getIdTipoRuc()) != null) {
                throw new PreexistingEntityException("Tiporuc " + tiporuc + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tiporuc tiporuc) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiporuc persistentTiporuc = em.find(Tiporuc.class, tiporuc.getIdTipoRuc());
            List<Ruc> rucListOld = persistentTiporuc.getRucList();
            List<Ruc> rucListNew = tiporuc.getRucList();
            List<String> illegalOrphanMessages = null;
            for (Ruc rucListOldRuc : rucListOld) {
                if (!rucListNew.contains(rucListOldRuc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ruc " + rucListOldRuc + " since its idTipoRuc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Ruc> attachedRucListNew = new ArrayList<Ruc>();
            for (Ruc rucListNewRucToAttach : rucListNew) {
                rucListNewRucToAttach = em.getReference(rucListNewRucToAttach.getClass(), rucListNewRucToAttach.getRuc());
                attachedRucListNew.add(rucListNewRucToAttach);
            }
            rucListNew = attachedRucListNew;
            tiporuc.setRucList(rucListNew);
            tiporuc = em.merge(tiporuc);
            for (Ruc rucListNewRuc : rucListNew) {
                if (!rucListOld.contains(rucListNewRuc)) {
                    Tiporuc oldIdTipoRucOfRucListNewRuc = rucListNewRuc.getIdTipoRuc();
                    rucListNewRuc.setIdTipoRuc(tiporuc);
                    rucListNewRuc = em.merge(rucListNewRuc);
                    if (oldIdTipoRucOfRucListNewRuc != null && !oldIdTipoRucOfRucListNewRuc.equals(tiporuc)) {
                        oldIdTipoRucOfRucListNewRuc.getRucList().remove(rucListNewRuc);
                        oldIdTipoRucOfRucListNewRuc = em.merge(oldIdTipoRucOfRucListNewRuc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tiporuc.getIdTipoRuc();
                if (findTiporuc(id) == null) {
                    throw new NonexistentEntityException("The tiporuc with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiporuc tiporuc;
            try {
                tiporuc = em.getReference(Tiporuc.class, id);
                tiporuc.getIdTipoRuc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiporuc with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ruc> rucListOrphanCheck = tiporuc.getRucList();
            for (Ruc rucListOrphanCheckRuc : rucListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tiporuc (" + tiporuc + ") cannot be destroyed since the Ruc " + rucListOrphanCheckRuc + " in its rucList field has a non-nullable idTipoRuc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tiporuc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tiporuc> findTiporucEntities() {
        return findTiporucEntities(true, -1, -1);
    }

    public List<Tiporuc> findTiporucEntities(int maxResults, int firstResult) {
        return findTiporucEntities(false, maxResults, firstResult);
    }

    private List<Tiporuc> findTiporucEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tiporuc.class));
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

    public Tiporuc findTiporuc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tiporuc.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiporucCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tiporuc> rt = cq.from(Tiporuc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
