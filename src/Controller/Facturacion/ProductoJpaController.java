/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Facturacion;

import Controller.Facturacion.exceptions.IllegalOrphanException;
import Controller.Facturacion.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DAO.Facturacion.Bodega;
import DAO.Facturacion.Categoria;
import DAO.Facturacion.Provedor;
import DAO.Facturacion.Detallefactura;
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
public class ProductoJpaController implements Serializable {

    public ProductoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SistemaFacturacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getDetallefacturaList() == null) {
            producto.setDetallefacturaList(new ArrayList<Detallefactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bodega idBodega = producto.getIdBodega();
            if (idBodega != null) {
                idBodega = em.getReference(idBodega.getClass(), idBodega.getIdBodega());
                producto.setIdBodega(idBodega);
            }
            Categoria idCategoria = producto.getIdCategoria();
            if (idCategoria != null) {
                idCategoria = em.getReference(idCategoria.getClass(), idCategoria.getIdCategoria());
                producto.setIdCategoria(idCategoria);
            }
            Provedor idProvedor = producto.getIdProvedor();
            if (idProvedor != null) {
                idProvedor = em.getReference(idProvedor.getClass(), idProvedor.getIdProvedor());
                producto.setIdProvedor(idProvedor);
            }
            List<Detallefactura> attachedDetallefacturaList = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListDetallefacturaToAttach : producto.getDetallefacturaList()) {
                detallefacturaListDetallefacturaToAttach = em.getReference(detallefacturaListDetallefacturaToAttach.getClass(), detallefacturaListDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaList.add(detallefacturaListDetallefacturaToAttach);
            }
            producto.setDetallefacturaList(attachedDetallefacturaList);
            em.persist(producto);
            if (idBodega != null) {
                idBodega.getProductoList().add(producto);
                idBodega = em.merge(idBodega);
            }
            if (idCategoria != null) {
                idCategoria.getProductoList().add(producto);
                idCategoria = em.merge(idCategoria);
            }
            if (idProvedor != null) {
                idProvedor.getProductoList().add(producto);
                idProvedor = em.merge(idProvedor);
            }
            for (Detallefactura detallefacturaListDetallefactura : producto.getDetallefacturaList()) {
                Producto oldProductoOfDetallefacturaListDetallefactura = detallefacturaListDetallefactura.getProducto();
                detallefacturaListDetallefactura.setProducto(producto);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
                if (oldProductoOfDetallefacturaListDetallefactura != null) {
                    oldProductoOfDetallefacturaListDetallefactura.getDetallefacturaList().remove(detallefacturaListDetallefactura);
                    oldProductoOfDetallefacturaListDetallefactura = em.merge(oldProductoOfDetallefacturaListDetallefactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getIdProducto());
            Bodega idBodegaOld = persistentProducto.getIdBodega();
            Bodega idBodegaNew = producto.getIdBodega();
            Categoria idCategoriaOld = persistentProducto.getIdCategoria();
            Categoria idCategoriaNew = producto.getIdCategoria();
            Provedor idProvedorOld = persistentProducto.getIdProvedor();
            Provedor idProvedorNew = producto.getIdProvedor();
            List<Detallefactura> detallefacturaListOld = persistentProducto.getDetallefacturaList();
            List<Detallefactura> detallefacturaListNew = producto.getDetallefacturaList();
            List<String> illegalOrphanMessages = null;
            for (Detallefactura detallefacturaListOldDetallefactura : detallefacturaListOld) {
                if (!detallefacturaListNew.contains(detallefacturaListOldDetallefactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallefactura " + detallefacturaListOldDetallefactura + " since its producto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idBodegaNew != null) {
                idBodegaNew = em.getReference(idBodegaNew.getClass(), idBodegaNew.getIdBodega());
                producto.setIdBodega(idBodegaNew);
            }
            if (idCategoriaNew != null) {
                idCategoriaNew = em.getReference(idCategoriaNew.getClass(), idCategoriaNew.getIdCategoria());
                producto.setIdCategoria(idCategoriaNew);
            }
            if (idProvedorNew != null) {
                idProvedorNew = em.getReference(idProvedorNew.getClass(), idProvedorNew.getIdProvedor());
                producto.setIdProvedor(idProvedorNew);
            }
            List<Detallefactura> attachedDetallefacturaListNew = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListNewDetallefacturaToAttach : detallefacturaListNew) {
                detallefacturaListNewDetallefacturaToAttach = em.getReference(detallefacturaListNewDetallefacturaToAttach.getClass(), detallefacturaListNewDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaListNew.add(detallefacturaListNewDetallefacturaToAttach);
            }
            detallefacturaListNew = attachedDetallefacturaListNew;
            producto.setDetallefacturaList(detallefacturaListNew);
            producto = em.merge(producto);
            if (idBodegaOld != null && !idBodegaOld.equals(idBodegaNew)) {
                idBodegaOld.getProductoList().remove(producto);
                idBodegaOld = em.merge(idBodegaOld);
            }
            if (idBodegaNew != null && !idBodegaNew.equals(idBodegaOld)) {
                idBodegaNew.getProductoList().add(producto);
                idBodegaNew = em.merge(idBodegaNew);
            }
            if (idCategoriaOld != null && !idCategoriaOld.equals(idCategoriaNew)) {
                idCategoriaOld.getProductoList().remove(producto);
                idCategoriaOld = em.merge(idCategoriaOld);
            }
            if (idCategoriaNew != null && !idCategoriaNew.equals(idCategoriaOld)) {
                idCategoriaNew.getProductoList().add(producto);
                idCategoriaNew = em.merge(idCategoriaNew);
            }
            if (idProvedorOld != null && !idProvedorOld.equals(idProvedorNew)) {
                idProvedorOld.getProductoList().remove(producto);
                idProvedorOld = em.merge(idProvedorOld);
            }
            if (idProvedorNew != null && !idProvedorNew.equals(idProvedorOld)) {
                idProvedorNew.getProductoList().add(producto);
                idProvedorNew = em.merge(idProvedorNew);
            }
            for (Detallefactura detallefacturaListNewDetallefactura : detallefacturaListNew) {
                if (!detallefacturaListOld.contains(detallefacturaListNewDetallefactura)) {
                    Producto oldProductoOfDetallefacturaListNewDetallefactura = detallefacturaListNewDetallefactura.getProducto();
                    detallefacturaListNewDetallefactura.setProducto(producto);
                    detallefacturaListNewDetallefactura = em.merge(detallefacturaListNewDetallefactura);
                    if (oldProductoOfDetallefacturaListNewDetallefactura != null && !oldProductoOfDetallefacturaListNewDetallefactura.equals(producto)) {
                        oldProductoOfDetallefacturaListNewDetallefactura.getDetallefacturaList().remove(detallefacturaListNewDetallefactura);
                        oldProductoOfDetallefacturaListNewDetallefactura = em.merge(oldProductoOfDetallefacturaListNewDetallefactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getIdProducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallefactura> detallefacturaListOrphanCheck = producto.getDetallefacturaList();
            for (Detallefactura detallefacturaListOrphanCheckDetallefactura : detallefacturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Detallefactura " + detallefacturaListOrphanCheckDetallefactura + " in its detallefacturaList field has a non-nullable producto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Bodega idBodega = producto.getIdBodega();
            if (idBodega != null) {
                idBodega.getProductoList().remove(producto);
                idBodega = em.merge(idBodega);
            }
            Categoria idCategoria = producto.getIdCategoria();
            if (idCategoria != null) {
                idCategoria.getProductoList().remove(producto);
                idCategoria = em.merge(idCategoria);
            }
            Provedor idProvedor = producto.getIdProvedor();
            if (idProvedor != null) {
                idProvedor.getProductoList().remove(producto);
                idProvedor = em.merge(idProvedor);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
