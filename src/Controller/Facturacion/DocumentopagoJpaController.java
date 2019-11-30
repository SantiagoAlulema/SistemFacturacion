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
import DAO.Facturacion.Cliente;
import DAO.Facturacion.Usuario;
import DAO.Facturacion.Devolucion;
import java.util.ArrayList;
import java.util.List;
import DAO.Facturacion.Consumidorfinal;
import DAO.Facturacion.Detallefactura;
import DAO.Facturacion.Documentopago;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sanch
 */
public class DocumentopagoJpaController implements Serializable {

    public DocumentopagoJpaController() {
        this.emf = this.emf = Persistence.createEntityManagerFactory("SistemaFacturacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Documentopago documentopago) {
        if (documentopago.getDevolucionList() == null) {
            documentopago.setDevolucionList(new ArrayList<Devolucion>());
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
            Cliente cliente = documentopago.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getClientePK());
                documentopago.setCliente(cliente);
            }
            Usuario usuario = documentopago.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getUsuarioPK());
                documentopago.setUsuario(usuario);
            }
            List<Devolucion> attachedDevolucionList = new ArrayList<Devolucion>();
            for (Devolucion devolucionListDevolucionToAttach : documentopago.getDevolucionList()) {
                devolucionListDevolucionToAttach = em.getReference(devolucionListDevolucionToAttach.getClass(), devolucionListDevolucionToAttach.getIdDevolucion());
                attachedDevolucionList.add(devolucionListDevolucionToAttach);
            }
            documentopago.setDevolucionList(attachedDevolucionList);
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
            if (cliente != null) {
                cliente.getDocumentopagoList().add(documentopago);
                cliente = em.merge(cliente);
            }
            if (usuario != null) {
                usuario.getDocumentopagoList().add(documentopago);
                usuario = em.merge(usuario);
            }
            for (Devolucion devolucionListDevolucion : documentopago.getDevolucionList()) {
                Documentopago oldFacturaidFacturaOfDevolucionListDevolucion = devolucionListDevolucion.getFacturaidFactura();
                devolucionListDevolucion.setFacturaidFactura(documentopago);
                devolucionListDevolucion = em.merge(devolucionListDevolucion);
                if (oldFacturaidFacturaOfDevolucionListDevolucion != null) {
                    oldFacturaidFacturaOfDevolucionListDevolucion.getDevolucionList().remove(devolucionListDevolucion);
                    oldFacturaidFacturaOfDevolucionListDevolucion = em.merge(oldFacturaidFacturaOfDevolucionListDevolucion);
                }
            }
            for (Consumidorfinal consumidorfinalListConsumidorfinal : documentopago.getConsumidorfinalList()) {
                Documentopago oldIdFacturaOfConsumidorfinalListConsumidorfinal = consumidorfinalListConsumidorfinal.getIdFactura();
                consumidorfinalListConsumidorfinal.setIdFactura(documentopago);
                consumidorfinalListConsumidorfinal = em.merge(consumidorfinalListConsumidorfinal);
                if (oldIdFacturaOfConsumidorfinalListConsumidorfinal != null) {
                    oldIdFacturaOfConsumidorfinalListConsumidorfinal.getConsumidorfinalList().remove(consumidorfinalListConsumidorfinal);
                    oldIdFacturaOfConsumidorfinalListConsumidorfinal = em.merge(oldIdFacturaOfConsumidorfinalListConsumidorfinal);
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
            Documentopago persistentDocumentopago = em.find(Documentopago.class, documentopago.getIdFactura());
            Cliente clienteOld = persistentDocumentopago.getCliente();
            Cliente clienteNew = documentopago.getCliente();
            Usuario usuarioOld = persistentDocumentopago.getUsuario();
            Usuario usuarioNew = documentopago.getUsuario();
            List<Devolucion> devolucionListOld = persistentDocumentopago.getDevolucionList();
            List<Devolucion> devolucionListNew = documentopago.getDevolucionList();
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
                    illegalOrphanMessages.add("You must retain Devolucion " + devolucionListOldDevolucion + " since its facturaidFactura field is not nullable.");
                }
            }
            for (Consumidorfinal consumidorfinalListOldConsumidorfinal : consumidorfinalListOld) {
                if (!consumidorfinalListNew.contains(consumidorfinalListOldConsumidorfinal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Consumidorfinal " + consumidorfinalListOldConsumidorfinal + " since its idFactura field is not nullable.");
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
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getClientePK());
                documentopago.setCliente(clienteNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getUsuarioPK());
                documentopago.setUsuario(usuarioNew);
            }
            List<Devolucion> attachedDevolucionListNew = new ArrayList<Devolucion>();
            for (Devolucion devolucionListNewDevolucionToAttach : devolucionListNew) {
                devolucionListNewDevolucionToAttach = em.getReference(devolucionListNewDevolucionToAttach.getClass(), devolucionListNewDevolucionToAttach.getIdDevolucion());
                attachedDevolucionListNew.add(devolucionListNewDevolucionToAttach);
            }
            devolucionListNew = attachedDevolucionListNew;
            documentopago.setDevolucionList(devolucionListNew);
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
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getDocumentopagoList().remove(documentopago);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getDocumentopagoList().add(documentopago);
                clienteNew = em.merge(clienteNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getDocumentopagoList().remove(documentopago);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getDocumentopagoList().add(documentopago);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Devolucion devolucionListNewDevolucion : devolucionListNew) {
                if (!devolucionListOld.contains(devolucionListNewDevolucion)) {
                    Documentopago oldFacturaidFacturaOfDevolucionListNewDevolucion = devolucionListNewDevolucion.getFacturaidFactura();
                    devolucionListNewDevolucion.setFacturaidFactura(documentopago);
                    devolucionListNewDevolucion = em.merge(devolucionListNewDevolucion);
                    if (oldFacturaidFacturaOfDevolucionListNewDevolucion != null && !oldFacturaidFacturaOfDevolucionListNewDevolucion.equals(documentopago)) {
                        oldFacturaidFacturaOfDevolucionListNewDevolucion.getDevolucionList().remove(devolucionListNewDevolucion);
                        oldFacturaidFacturaOfDevolucionListNewDevolucion = em.merge(oldFacturaidFacturaOfDevolucionListNewDevolucion);
                    }
                }
            }
            for (Consumidorfinal consumidorfinalListNewConsumidorfinal : consumidorfinalListNew) {
                if (!consumidorfinalListOld.contains(consumidorfinalListNewConsumidorfinal)) {
                    Documentopago oldIdFacturaOfConsumidorfinalListNewConsumidorfinal = consumidorfinalListNewConsumidorfinal.getIdFactura();
                    consumidorfinalListNewConsumidorfinal.setIdFactura(documentopago);
                    consumidorfinalListNewConsumidorfinal = em.merge(consumidorfinalListNewConsumidorfinal);
                    if (oldIdFacturaOfConsumidorfinalListNewConsumidorfinal != null && !oldIdFacturaOfConsumidorfinalListNewConsumidorfinal.equals(documentopago)) {
                        oldIdFacturaOfConsumidorfinalListNewConsumidorfinal.getConsumidorfinalList().remove(consumidorfinalListNewConsumidorfinal);
                        oldIdFacturaOfConsumidorfinalListNewConsumidorfinal = em.merge(oldIdFacturaOfConsumidorfinalListNewConsumidorfinal);
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
                Integer id = documentopago.getIdFactura();
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
                documentopago.getIdFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The documentopago with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Devolucion> devolucionListOrphanCheck = documentopago.getDevolucionList();
            for (Devolucion devolucionListOrphanCheckDevolucion : devolucionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Documentopago (" + documentopago + ") cannot be destroyed since the Devolucion " + devolucionListOrphanCheckDevolucion + " in its devolucionList field has a non-nullable facturaidFactura field.");
            }
            List<Consumidorfinal> consumidorfinalListOrphanCheck = documentopago.getConsumidorfinalList();
            for (Consumidorfinal consumidorfinalListOrphanCheckConsumidorfinal : consumidorfinalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Documentopago (" + documentopago + ") cannot be destroyed since the Consumidorfinal " + consumidorfinalListOrphanCheckConsumidorfinal + " in its consumidorfinalList field has a non-nullable idFactura field.");
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
            Cliente cliente = documentopago.getCliente();
            if (cliente != null) {
                cliente.getDocumentopagoList().remove(documentopago);
                cliente = em.merge(cliente);
            }
            Usuario usuario = documentopago.getUsuario();
            if (usuario != null) {
                usuario.getDocumentopagoList().remove(documentopago);
                usuario = em.merge(usuario);
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
