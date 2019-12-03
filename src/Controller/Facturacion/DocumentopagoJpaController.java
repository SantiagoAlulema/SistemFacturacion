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
import DAO.Facturacion.Tienda;
import DAO.Facturacion.Usuario;
import DAO.Facturacion.Devolucion;
import java.util.ArrayList;
import java.util.List;
import DAO.Facturacion.Factura;
import DAO.Facturacion.Consumidorfinal;
import DAO.Facturacion.Detallefactura;
import DAO.Facturacion.Documentopago;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Santiago
 */
public class DocumentopagoJpaController implements Serializable {

    public DocumentopagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Documentopago documentopago) {
        if (documentopago.getDevolucionList() == null) {
            documentopago.setDevolucionList(new ArrayList<Devolucion>());
        }
        if (documentopago.getFacturaList() == null) {
            documentopago.setFacturaList(new ArrayList<Factura>());
        }
        if (documentopago.getConsumidorfinalList() == null) {
            documentopago.setConsumidorfinalList(new ArrayList<Consumidorfinal>());
        }
        if (documentopago.getDetallefacturaList() == null) {
            documentopago.setDetallefacturaList(new ArrayList<Detallefactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tienda idTienda = documentopago.getIdTienda();
            if (idTienda != null) {
                idTienda = em.getReference(idTienda.getClass(), idTienda.getIdTienda());
                documentopago.setIdTienda(idTienda);
            }
            Usuario usuarioUsuario = documentopago.getUsuarioUsuario();
            if (usuarioUsuario != null) {
                usuarioUsuario = em.getReference(usuarioUsuario.getClass(), usuarioUsuario.getCedula());
                documentopago.setUsuarioUsuario(usuarioUsuario);
            }
            List<Devolucion> attachedDevolucionList = new ArrayList<Devolucion>();
            for (Devolucion devolucionListDevolucionToAttach : documentopago.getDevolucionList()) {
                devolucionListDevolucionToAttach = em.getReference(devolucionListDevolucionToAttach.getClass(), devolucionListDevolucionToAttach.getIdDevolucion());
                attachedDevolucionList.add(devolucionListDevolucionToAttach);
            }
            documentopago.setDevolucionList(attachedDevolucionList);
            List<Factura> attachedFacturaList = new ArrayList<Factura>();
            for (Factura facturaListFacturaToAttach : documentopago.getFacturaList()) {
                facturaListFacturaToAttach = em.getReference(facturaListFacturaToAttach.getClass(), facturaListFacturaToAttach.getIdFactura());
                attachedFacturaList.add(facturaListFacturaToAttach);
            }
            documentopago.setFacturaList(attachedFacturaList);
            List<Consumidorfinal> attachedConsumidorfinalList = new ArrayList<Consumidorfinal>();
            for (Consumidorfinal consumidorfinalListConsumidorfinalToAttach : documentopago.getConsumidorfinalList()) {
                consumidorfinalListConsumidorfinalToAttach = em.getReference(consumidorfinalListConsumidorfinalToAttach.getClass(), consumidorfinalListConsumidorfinalToAttach.getIdConsumidorFinal());
                attachedConsumidorfinalList.add(consumidorfinalListConsumidorfinalToAttach);
            }
            documentopago.setConsumidorfinalList(attachedConsumidorfinalList);
            List<Detallefactura> attachedDetallefacturaList = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListDetallefacturaToAttach : documentopago.getDetallefacturaList()) {
                detallefacturaListDetallefacturaToAttach = em.getReference(detallefacturaListDetallefacturaToAttach.getClass(), detallefacturaListDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaList.add(detallefacturaListDetallefacturaToAttach);
            }
            documentopago.setDetallefacturaList(attachedDetallefacturaList);
            em.persist(documentopago);
            if (idTienda != null) {
                idTienda.getDocumentopagoList().add(documentopago);
                idTienda = em.merge(idTienda);
            }
            if (usuarioUsuario != null) {
                usuarioUsuario.getDocumentopagoList().add(documentopago);
                usuarioUsuario = em.merge(usuarioUsuario);
            }
            for (Devolucion devolucionListDevolucion : documentopago.getDevolucionList()) {
                Documentopago oldIdFacturaOfDevolucionListDevolucion = devolucionListDevolucion.getIdFactura();
                devolucionListDevolucion.setIdFactura(documentopago);
                devolucionListDevolucion = em.merge(devolucionListDevolucion);
                if (oldIdFacturaOfDevolucionListDevolucion != null) {
                    oldIdFacturaOfDevolucionListDevolucion.getDevolucionList().remove(devolucionListDevolucion);
                    oldIdFacturaOfDevolucionListDevolucion = em.merge(oldIdFacturaOfDevolucionListDevolucion);
                }
            }
            for (Factura facturaListFactura : documentopago.getFacturaList()) {
                Documentopago oldIdDocumentoOfFacturaListFactura = facturaListFactura.getIdDocumento();
                facturaListFactura.setIdDocumento(documentopago);
                facturaListFactura = em.merge(facturaListFactura);
                if (oldIdDocumentoOfFacturaListFactura != null) {
                    oldIdDocumentoOfFacturaListFactura.getFacturaList().remove(facturaListFactura);
                    oldIdDocumentoOfFacturaListFactura = em.merge(oldIdDocumentoOfFacturaListFactura);
                }
            }
            for (Consumidorfinal consumidorfinalListConsumidorfinal : documentopago.getConsumidorfinalList()) {
                Documentopago oldIdDocumentoOfConsumidorfinalListConsumidorfinal = consumidorfinalListConsumidorfinal.getIdDocumento();
                consumidorfinalListConsumidorfinal.setIdDocumento(documentopago);
                consumidorfinalListConsumidorfinal = em.merge(consumidorfinalListConsumidorfinal);
                if (oldIdDocumentoOfConsumidorfinalListConsumidorfinal != null) {
                    oldIdDocumentoOfConsumidorfinalListConsumidorfinal.getConsumidorfinalList().remove(consumidorfinalListConsumidorfinal);
                    oldIdDocumentoOfConsumidorfinalListConsumidorfinal = em.merge(oldIdDocumentoOfConsumidorfinalListConsumidorfinal);
                }
            }
            for (Detallefactura detallefacturaListDetallefactura : documentopago.getDetallefacturaList()) {
                Documentopago oldDocumentopagoOfDetallefacturaListDetallefactura = detallefacturaListDetallefactura.getDocumentopago();
                detallefacturaListDetallefactura.setDocumentopago(documentopago);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
                if (oldDocumentopagoOfDetallefacturaListDetallefactura != null) {
                    oldDocumentopagoOfDetallefacturaListDetallefactura.getDetallefacturaList().remove(detallefacturaListDetallefactura);
                    oldDocumentopagoOfDetallefacturaListDetallefactura = em.merge(oldDocumentopagoOfDetallefacturaListDetallefactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Documentopago documentopago) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documentopago persistentDocumentopago = em.find(Documentopago.class, documentopago.getIdDocumento());
            Tienda idTiendaOld = persistentDocumentopago.getIdTienda();
            Tienda idTiendaNew = documentopago.getIdTienda();
            Usuario usuarioUsuarioOld = persistentDocumentopago.getUsuarioUsuario();
            Usuario usuarioUsuarioNew = documentopago.getUsuarioUsuario();
            List<Devolucion> devolucionListOld = persistentDocumentopago.getDevolucionList();
            List<Devolucion> devolucionListNew = documentopago.getDevolucionList();
            List<Factura> facturaListOld = persistentDocumentopago.getFacturaList();
            List<Factura> facturaListNew = documentopago.getFacturaList();
            List<Consumidorfinal> consumidorfinalListOld = persistentDocumentopago.getConsumidorfinalList();
            List<Consumidorfinal> consumidorfinalListNew = documentopago.getConsumidorfinalList();
            List<Detallefactura> detallefacturaListOld = persistentDocumentopago.getDetallefacturaList();
            List<Detallefactura> detallefacturaListNew = documentopago.getDetallefacturaList();
            List<String> illegalOrphanMessages = null;
            for (Devolucion devolucionListOldDevolucion : devolucionListOld) {
                if (!devolucionListNew.contains(devolucionListOldDevolucion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Devolucion " + devolucionListOldDevolucion + " since its idFactura field is not nullable.");
                }
            }
            for (Factura facturaListOldFactura : facturaListOld) {
                if (!facturaListNew.contains(facturaListOldFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Factura " + facturaListOldFactura + " since its idDocumento field is not nullable.");
                }
            }
            for (Consumidorfinal consumidorfinalListOldConsumidorfinal : consumidorfinalListOld) {
                if (!consumidorfinalListNew.contains(consumidorfinalListOldConsumidorfinal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Consumidorfinal " + consumidorfinalListOldConsumidorfinal + " since its idDocumento field is not nullable.");
                }
            }
            for (Detallefactura detallefacturaListOldDetallefactura : detallefacturaListOld) {
                if (!detallefacturaListNew.contains(detallefacturaListOldDetallefactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallefactura " + detallefacturaListOldDetallefactura + " since its documentopago field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTiendaNew != null) {
                idTiendaNew = em.getReference(idTiendaNew.getClass(), idTiendaNew.getIdTienda());
                documentopago.setIdTienda(idTiendaNew);
            }
            if (usuarioUsuarioNew != null) {
                usuarioUsuarioNew = em.getReference(usuarioUsuarioNew.getClass(), usuarioUsuarioNew.getCedula());
                documentopago.setUsuarioUsuario(usuarioUsuarioNew);
            }
            List<Devolucion> attachedDevolucionListNew = new ArrayList<Devolucion>();
            for (Devolucion devolucionListNewDevolucionToAttach : devolucionListNew) {
                devolucionListNewDevolucionToAttach = em.getReference(devolucionListNewDevolucionToAttach.getClass(), devolucionListNewDevolucionToAttach.getIdDevolucion());
                attachedDevolucionListNew.add(devolucionListNewDevolucionToAttach);
            }
            devolucionListNew = attachedDevolucionListNew;
            documentopago.setDevolucionList(devolucionListNew);
            List<Factura> attachedFacturaListNew = new ArrayList<Factura>();
            for (Factura facturaListNewFacturaToAttach : facturaListNew) {
                facturaListNewFacturaToAttach = em.getReference(facturaListNewFacturaToAttach.getClass(), facturaListNewFacturaToAttach.getIdFactura());
                attachedFacturaListNew.add(facturaListNewFacturaToAttach);
            }
            facturaListNew = attachedFacturaListNew;
            documentopago.setFacturaList(facturaListNew);
            List<Consumidorfinal> attachedConsumidorfinalListNew = new ArrayList<Consumidorfinal>();
            for (Consumidorfinal consumidorfinalListNewConsumidorfinalToAttach : consumidorfinalListNew) {
                consumidorfinalListNewConsumidorfinalToAttach = em.getReference(consumidorfinalListNewConsumidorfinalToAttach.getClass(), consumidorfinalListNewConsumidorfinalToAttach.getIdConsumidorFinal());
                attachedConsumidorfinalListNew.add(consumidorfinalListNewConsumidorfinalToAttach);
            }
            consumidorfinalListNew = attachedConsumidorfinalListNew;
            documentopago.setConsumidorfinalList(consumidorfinalListNew);
            List<Detallefactura> attachedDetallefacturaListNew = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListNewDetallefacturaToAttach : detallefacturaListNew) {
                detallefacturaListNewDetallefacturaToAttach = em.getReference(detallefacturaListNewDetallefacturaToAttach.getClass(), detallefacturaListNewDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaListNew.add(detallefacturaListNewDetallefacturaToAttach);
            }
            detallefacturaListNew = attachedDetallefacturaListNew;
            documentopago.setDetallefacturaList(detallefacturaListNew);
            documentopago = em.merge(documentopago);
            if (idTiendaOld != null && !idTiendaOld.equals(idTiendaNew)) {
                idTiendaOld.getDocumentopagoList().remove(documentopago);
                idTiendaOld = em.merge(idTiendaOld);
            }
            if (idTiendaNew != null && !idTiendaNew.equals(idTiendaOld)) {
                idTiendaNew.getDocumentopagoList().add(documentopago);
                idTiendaNew = em.merge(idTiendaNew);
            }
            if (usuarioUsuarioOld != null && !usuarioUsuarioOld.equals(usuarioUsuarioNew)) {
                usuarioUsuarioOld.getDocumentopagoList().remove(documentopago);
                usuarioUsuarioOld = em.merge(usuarioUsuarioOld);
            }
            if (usuarioUsuarioNew != null && !usuarioUsuarioNew.equals(usuarioUsuarioOld)) {
                usuarioUsuarioNew.getDocumentopagoList().add(documentopago);
                usuarioUsuarioNew = em.merge(usuarioUsuarioNew);
            }
            for (Devolucion devolucionListNewDevolucion : devolucionListNew) {
                if (!devolucionListOld.contains(devolucionListNewDevolucion)) {
                    Documentopago oldIdFacturaOfDevolucionListNewDevolucion = devolucionListNewDevolucion.getIdFactura();
                    devolucionListNewDevolucion.setIdFactura(documentopago);
                    devolucionListNewDevolucion = em.merge(devolucionListNewDevolucion);
                    if (oldIdFacturaOfDevolucionListNewDevolucion != null && !oldIdFacturaOfDevolucionListNewDevolucion.equals(documentopago)) {
                        oldIdFacturaOfDevolucionListNewDevolucion.getDevolucionList().remove(devolucionListNewDevolucion);
                        oldIdFacturaOfDevolucionListNewDevolucion = em.merge(oldIdFacturaOfDevolucionListNewDevolucion);
                    }
                }
            }
            for (Factura facturaListNewFactura : facturaListNew) {
                if (!facturaListOld.contains(facturaListNewFactura)) {
                    Documentopago oldIdDocumentoOfFacturaListNewFactura = facturaListNewFactura.getIdDocumento();
                    facturaListNewFactura.setIdDocumento(documentopago);
                    facturaListNewFactura = em.merge(facturaListNewFactura);
                    if (oldIdDocumentoOfFacturaListNewFactura != null && !oldIdDocumentoOfFacturaListNewFactura.equals(documentopago)) {
                        oldIdDocumentoOfFacturaListNewFactura.getFacturaList().remove(facturaListNewFactura);
                        oldIdDocumentoOfFacturaListNewFactura = em.merge(oldIdDocumentoOfFacturaListNewFactura);
                    }
                }
            }
            for (Consumidorfinal consumidorfinalListNewConsumidorfinal : consumidorfinalListNew) {
                if (!consumidorfinalListOld.contains(consumidorfinalListNewConsumidorfinal)) {
                    Documentopago oldIdDocumentoOfConsumidorfinalListNewConsumidorfinal = consumidorfinalListNewConsumidorfinal.getIdDocumento();
                    consumidorfinalListNewConsumidorfinal.setIdDocumento(documentopago);
                    consumidorfinalListNewConsumidorfinal = em.merge(consumidorfinalListNewConsumidorfinal);
                    if (oldIdDocumentoOfConsumidorfinalListNewConsumidorfinal != null && !oldIdDocumentoOfConsumidorfinalListNewConsumidorfinal.equals(documentopago)) {
                        oldIdDocumentoOfConsumidorfinalListNewConsumidorfinal.getConsumidorfinalList().remove(consumidorfinalListNewConsumidorfinal);
                        oldIdDocumentoOfConsumidorfinalListNewConsumidorfinal = em.merge(oldIdDocumentoOfConsumidorfinalListNewConsumidorfinal);
                    }
                }
            }
            for (Detallefactura detallefacturaListNewDetallefactura : detallefacturaListNew) {
                if (!detallefacturaListOld.contains(detallefacturaListNewDetallefactura)) {
                    Documentopago oldDocumentopagoOfDetallefacturaListNewDetallefactura = detallefacturaListNewDetallefactura.getDocumentopago();
                    detallefacturaListNewDetallefactura.setDocumentopago(documentopago);
                    detallefacturaListNewDetallefactura = em.merge(detallefacturaListNewDetallefactura);
                    if (oldDocumentopagoOfDetallefacturaListNewDetallefactura != null && !oldDocumentopagoOfDetallefacturaListNewDetallefactura.equals(documentopago)) {
                        oldDocumentopagoOfDetallefacturaListNewDetallefactura.getDetallefacturaList().remove(detallefacturaListNewDetallefactura);
                        oldDocumentopagoOfDetallefacturaListNewDetallefactura = em.merge(oldDocumentopagoOfDetallefacturaListNewDetallefactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = documentopago.getIdDocumento();
                if (findDocumentopago(id) == null) {
                    throw new NonexistentEntityException("The documentopago with id " + id + " no longer exists.");
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
            Documentopago documentopago;
            try {
                documentopago = em.getReference(Documentopago.class, id);
                documentopago.getIdDocumento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The documentopago with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Devolucion> devolucionListOrphanCheck = documentopago.getDevolucionList();
            for (Devolucion devolucionListOrphanCheckDevolucion : devolucionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Documentopago (" + documentopago + ") cannot be destroyed since the Devolucion " + devolucionListOrphanCheckDevolucion + " in its devolucionList field has a non-nullable idFactura field.");
            }
            List<Factura> facturaListOrphanCheck = documentopago.getFacturaList();
            for (Factura facturaListOrphanCheckFactura : facturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Documentopago (" + documentopago + ") cannot be destroyed since the Factura " + facturaListOrphanCheckFactura + " in its facturaList field has a non-nullable idDocumento field.");
            }
            List<Consumidorfinal> consumidorfinalListOrphanCheck = documentopago.getConsumidorfinalList();
            for (Consumidorfinal consumidorfinalListOrphanCheckConsumidorfinal : consumidorfinalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Documentopago (" + documentopago + ") cannot be destroyed since the Consumidorfinal " + consumidorfinalListOrphanCheckConsumidorfinal + " in its consumidorfinalList field has a non-nullable idDocumento field.");
            }
            List<Detallefactura> detallefacturaListOrphanCheck = documentopago.getDetallefacturaList();
            for (Detallefactura detallefacturaListOrphanCheckDetallefactura : detallefacturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Documentopago (" + documentopago + ") cannot be destroyed since the Detallefactura " + detallefacturaListOrphanCheckDetallefactura + " in its detallefacturaList field has a non-nullable documentopago field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tienda idTienda = documentopago.getIdTienda();
            if (idTienda != null) {
                idTienda.getDocumentopagoList().remove(documentopago);
                idTienda = em.merge(idTienda);
            }
            Usuario usuarioUsuario = documentopago.getUsuarioUsuario();
            if (usuarioUsuario != null) {
                usuarioUsuario.getDocumentopagoList().remove(documentopago);
                usuarioUsuario = em.merge(usuarioUsuario);
            }
            em.remove(documentopago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Documentopago> findDocumentopagoEntities() {
        return findDocumentopagoEntities(true, -1, -1);
    }

    public List<Documentopago> findDocumentopagoEntities(int maxResults, int firstResult) {
        return findDocumentopagoEntities(false, maxResults, firstResult);
    }

    private List<Documentopago> findDocumentopagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Documentopago.class));
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

    public Documentopago findDocumentopago(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Documentopago.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocumentopagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Documentopago> rt = cq.from(Documentopago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
