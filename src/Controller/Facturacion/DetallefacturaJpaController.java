/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Facturacion;

import Controller.Facturacion.exceptions.NonexistentEntityException;
import Controller.Facturacion.exceptions.PreexistingEntityException;
import DAO.Facturacion.Detallefactura;
import DAO.Facturacion.DetallefacturaPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DAO.Facturacion.Documentopago;
import DAO.Facturacion.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sanch
 */
public class DetallefacturaJpaController implements Serializable {

    public DetallefacturaJpaController() {
        this.emf = this.emf = Persistence.createEntityManagerFactory("SistemaFacturacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallefactura detallefactura) throws PreexistingEntityException, Exception {
        if (detallefactura.getDetallefacturaPK() == null) {
            detallefactura.setDetallefacturaPK(new DetallefacturaPK());
        }
        detallefactura.getDetallefacturaPK().setProductoidProducto(detallefactura.getProducto().getProductoPK().getIdProducto());
        detallefactura.getDetallefacturaPK().setFacturaidFactura(detallefactura.getDocumentopago().getIdFactura());
        detallefactura.getDetallefacturaPK().setProductoCodProducto(detallefactura.getProducto().getProductoPK().getCodProducto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documentopago documentopago = detallefactura.getDocumentopago();
            if (documentopago != null) {
                documentopago = em.getReference(documentopago.getClass(), documentopago.getIdFactura());
                detallefactura.setDocumentopago(documentopago);
            }
            Producto producto = detallefactura.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getProductoPK());
                detallefactura.setProducto(producto);
            }
            em.persist(detallefactura);
            if (documentopago != null) {
                documentopago.getDetallefacturaList().add(detallefactura);
                documentopago = em.merge(documentopago);
            }
            if (producto != null) {
                producto.getDetallefacturaList().add(detallefactura);
                producto = em.merge(producto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallefactura(detallefactura.getDetallefacturaPK()) != null) {
                throw new PreexistingEntityException("Detallefactura " + detallefactura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallefactura detallefactura) throws NonexistentEntityException, Exception {
        detallefactura.getDetallefacturaPK().setProductoidProducto(detallefactura.getProducto().getProductoPK().getIdProducto());
        detallefactura.getDetallefacturaPK().setFacturaidFactura(detallefactura.getDocumentopago().getIdFactura());
        detallefactura.getDetallefacturaPK().setProductoCodProducto(detallefactura.getProducto().getProductoPK().getCodProducto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura persistentDetallefactura = em.find(Detallefactura.class, detallefactura.getDetallefacturaPK());
            Documentopago documentopagoOld = persistentDetallefactura.getDocumentopago();
            Documentopago documentopagoNew = detallefactura.getDocumentopago();
            Producto productoOld = persistentDetallefactura.getProducto();
            Producto productoNew = detallefactura.getProducto();
            if (documentopagoNew != null) {
                documentopagoNew = em.getReference(documentopagoNew.getClass(), documentopagoNew.getIdFactura());
                detallefactura.setDocumentopago(documentopagoNew);
            }
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getProductoPK());
                detallefactura.setProducto(productoNew);
            }
            detallefactura = em.merge(detallefactura);
            if (documentopagoOld != null && !documentopagoOld.equals(documentopagoNew)) {
                documentopagoOld.getDetallefacturaList().remove(detallefactura);
                documentopagoOld = em.merge(documentopagoOld);
            }
            if (documentopagoNew != null && !documentopagoNew.equals(documentopagoOld)) {
                documentopagoNew.getDetallefacturaList().add(detallefactura);
                documentopagoNew = em.merge(documentopagoNew);
            }
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getDetallefacturaList().remove(detallefactura);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getDetallefacturaList().add(detallefactura);
                productoNew = em.merge(productoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetallefacturaPK id = detallefactura.getDetallefacturaPK();
                if (findDetallefactura(id) == null) {
                    throw new NonexistentEntityException("The detallefactura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetallefacturaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallefactura detallefactura;
            try {
                detallefactura = em.getReference(Detallefactura.class, id);
                detallefactura.getDetallefacturaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallefactura with id " + id + " no longer exists.", enfe);
            }
            Documentopago documentopago = detallefactura.getDocumentopago();
            if (documentopago != null) {
                documentopago.getDetallefacturaList().remove(detallefactura);
                documentopago = em.merge(documentopago);
            }
            Producto producto = detallefactura.getProducto();
            if (producto != null) {
                producto.getDetallefacturaList().remove(detallefactura);
                producto = em.merge(producto);
            }
            em.remove(detallefactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallefactura> findDetallefacturaEntities() {
        return findDetallefacturaEntities(true, -1, -1);
    }

    public List<Detallefactura> findDetallefacturaEntities(int maxResults, int firstResult) {
        return findDetallefacturaEntities(false, maxResults, firstResult);
    }

    private List<Detallefactura> findDetallefacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallefactura.class));
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

    public Detallefactura findDetallefactura(DetallefacturaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallefactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallefacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallefactura> rt = cq.from(Detallefactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
