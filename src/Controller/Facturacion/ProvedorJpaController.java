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
import DAO.Facturacion.Producto;
import DAO.Facturacion.Provedor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sanch
 */
public class ProvedorJpaController implements Serializable {

    public ProvedorJpaController() {
        this.emf = this.emf = Persistence.createEntityManagerFactory("SistemaFacturacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Provedor provedor) throws PreexistingEntityException, Exception {
        if (provedor.getProductoList() == null) {
            provedor.setProductoList(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Producto> attachedProductoList = new ArrayList<Producto>();
            for (Producto productoListProductoToAttach : provedor.getProductoList()) {
                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getProductoPK());
                attachedProductoList.add(productoListProductoToAttach);
            }
            provedor.setProductoList(attachedProductoList);
            em.persist(provedor);
            for (Producto productoListProducto : provedor.getProductoList()) {
                Provedor oldIdProvedorOfProductoListProducto = productoListProducto.getIdProvedor();
                productoListProducto.setIdProvedor(provedor);
                productoListProducto = em.merge(productoListProducto);
                if (oldIdProvedorOfProductoListProducto != null) {
                    oldIdProvedorOfProductoListProducto.getProductoList().remove(productoListProducto);
                    oldIdProvedorOfProductoListProducto = em.merge(oldIdProvedorOfProductoListProducto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProvedor(provedor.getIdProvedor()) != null) {
                throw new PreexistingEntityException("Provedor " + provedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Provedor provedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provedor persistentProvedor = em.find(Provedor.class, provedor.getIdProvedor());
            List<Producto> productoListOld = persistentProvedor.getProductoList();
            List<Producto> productoListNew = provedor.getProductoList();
            List<String> illegalOrphanMessages = null;
            for (Producto productoListOldProducto : productoListOld) {
                if (!productoListNew.contains(productoListOldProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Producto " + productoListOldProducto + " since its idProvedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Producto> attachedProductoListNew = new ArrayList<Producto>();
            for (Producto productoListNewProductoToAttach : productoListNew) {
                productoListNewProductoToAttach = em.getReference(productoListNewProductoToAttach.getClass(), productoListNewProductoToAttach.getProductoPK());
                attachedProductoListNew.add(productoListNewProductoToAttach);
            }
            productoListNew = attachedProductoListNew;
            provedor.setProductoList(productoListNew);
            provedor = em.merge(provedor);
            for (Producto productoListNewProducto : productoListNew) {
                if (!productoListOld.contains(productoListNewProducto)) {
                    Provedor oldIdProvedorOfProductoListNewProducto = productoListNewProducto.getIdProvedor();
                    productoListNewProducto.setIdProvedor(provedor);
                    productoListNewProducto = em.merge(productoListNewProducto);
                    if (oldIdProvedorOfProductoListNewProducto != null && !oldIdProvedorOfProductoListNewProducto.equals(provedor)) {
                        oldIdProvedorOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
                        oldIdProvedorOfProductoListNewProducto = em.merge(oldIdProvedorOfProductoListNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = provedor.getIdProvedor();
                if (findProvedor(id) == null) {
                    throw new NonexistentEntityException("The provedor with id " + id + " no longer exists.");
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
            Provedor provedor;
            try {
                provedor = em.getReference(Provedor.class, id);
                provedor.getIdProvedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The provedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Producto> productoListOrphanCheck = provedor.getProductoList();
            for (Producto productoListOrphanCheckProducto : productoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Provedor (" + provedor + ") cannot be destroyed since the Producto " + productoListOrphanCheckProducto + " in its productoList field has a non-nullable idProvedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(provedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Provedor> findProvedorEntities() {
        return findProvedorEntities(true, -1, -1);
    }

    public List<Provedor> findProvedorEntities(int maxResults, int firstResult) {
        return findProvedorEntities(false, maxResults, firstResult);
    }

    private List<Provedor> findProvedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Provedor.class));
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

    public Provedor findProvedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Provedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProvedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Provedor> rt = cq.from(Provedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
