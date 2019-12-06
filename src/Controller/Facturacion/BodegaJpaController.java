/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Facturacion;

import Controller.Facturacion.exceptions.IllegalOrphanException;
import Controller.Facturacion.exceptions.NonexistentEntityException;
import Controller.Facturacion.exceptions.PreexistingEntityException;
import DAO.Facturacion.Bodega;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DAO.Facturacion.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Santiago
 */
public class BodegaJpaController implements Serializable {

    public BodegaJpaController() {

        this.emf = Persistence.createEntityManagerFactory("SistemaFacturacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bodega bodega) throws PreexistingEntityException, Exception {
        if (bodega.getProductoList() == null) {
            bodega.setProductoList(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Producto> attachedProductoList = new ArrayList<Producto>();
            for (Producto productoListProductoToAttach : bodega.getProductoList()) {
                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getIdProducto());
                attachedProductoList.add(productoListProductoToAttach);
            }
            bodega.setProductoList(attachedProductoList);
            em.persist(bodega);
            for (Producto productoListProducto : bodega.getProductoList()) {
                Bodega oldIdBodegaOfProductoListProducto = productoListProducto.getIdBodega();
                productoListProducto.setIdBodega(bodega);
                productoListProducto = em.merge(productoListProducto);
                if (oldIdBodegaOfProductoListProducto != null) {
                    oldIdBodegaOfProductoListProducto.getProductoList().remove(productoListProducto);
                    oldIdBodegaOfProductoListProducto = em.merge(oldIdBodegaOfProductoListProducto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBodega(bodega.getIdBodega()) != null) {
                throw new PreexistingEntityException("Bodega " + bodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bodega bodega) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bodega persistentBodega = em.find(Bodega.class, bodega.getIdBodega());
            List<Producto> productoListOld = persistentBodega.getProductoList();
            List<Producto> productoListNew = bodega.getProductoList();
            List<String> illegalOrphanMessages = null;
            for (Producto productoListOldProducto : productoListOld) {
                if (!productoListNew.contains(productoListOldProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Producto " + productoListOldProducto + " since its idBodega field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Producto> attachedProductoListNew = new ArrayList<Producto>();
            for (Producto productoListNewProductoToAttach : productoListNew) {
                productoListNewProductoToAttach = em.getReference(productoListNewProductoToAttach.getClass(), productoListNewProductoToAttach.getIdProducto());
                attachedProductoListNew.add(productoListNewProductoToAttach);
            }
            productoListNew = attachedProductoListNew;
            bodega.setProductoList(productoListNew);
            bodega = em.merge(bodega);
            for (Producto productoListNewProducto : productoListNew) {
                if (!productoListOld.contains(productoListNewProducto)) {
                    Bodega oldIdBodegaOfProductoListNewProducto = productoListNewProducto.getIdBodega();
                    productoListNewProducto.setIdBodega(bodega);
                    productoListNewProducto = em.merge(productoListNewProducto);
                    if (oldIdBodegaOfProductoListNewProducto != null && !oldIdBodegaOfProductoListNewProducto.equals(bodega)) {
                        oldIdBodegaOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
                        oldIdBodegaOfProductoListNewProducto = em.merge(oldIdBodegaOfProductoListNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bodega.getIdBodega();
                if (findBodega(id) == null) {
                    throw new NonexistentEntityException("The bodega with id " + id + " no longer exists.");
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
            Bodega bodega;
            try {
                bodega = em.getReference(Bodega.class, id);
                bodega.getIdBodega();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bodega with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Producto> productoListOrphanCheck = bodega.getProductoList();
            for (Producto productoListOrphanCheckProducto : productoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Bodega (" + bodega + ") cannot be destroyed since the Producto " + productoListOrphanCheckProducto + " in its productoList field has a non-nullable idBodega field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(bodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bodega> findBodegaEntities() {
        return findBodegaEntities(true, -1, -1);
    }

    public List<Bodega> findBodegaEntities(int maxResults, int firstResult) {
        return findBodegaEntities(false, maxResults, firstResult);
    }

    private List<Bodega> findBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bodega.class));
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

    public Bodega findBodega(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bodega> rt = cq.from(Bodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
