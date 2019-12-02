/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Facturacion;

import Controller.Facturacion.exceptions.IllegalOrphanException;
import Controller.Facturacion.exceptions.NonexistentEntityException;
import Controller.Facturacion.exceptions.PreexistingEntityException;
import DAO.Facturacion.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DAO.Facturacion.Documentopago;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Santiago
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SistemaFacturacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getDocumentopagoList() == null) {
            cliente.setDocumentopagoList(new ArrayList<Documentopago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Documentopago> attachedDocumentopagoList = new ArrayList<Documentopago>();
            for (Documentopago documentopagoListDocumentopagoToAttach : cliente.getDocumentopagoList()) {
                documentopagoListDocumentopagoToAttach = em.getReference(documentopagoListDocumentopagoToAttach.getClass(), documentopagoListDocumentopagoToAttach.getIdDocumento());
                attachedDocumentopagoList.add(documentopagoListDocumentopagoToAttach);
            }
            cliente.setDocumentopagoList(attachedDocumentopagoList);
            em.persist(cliente);
            for (Documentopago documentopagoListDocumentopago : cliente.getDocumentopagoList()) {
                Cliente oldCedulaOfDocumentopagoListDocumentopago = documentopagoListDocumentopago.getCedula();
                documentopagoListDocumentopago.setCedula(cliente);
                documentopagoListDocumentopago = em.merge(documentopagoListDocumentopago);
                if (oldCedulaOfDocumentopagoListDocumentopago != null) {
                    oldCedulaOfDocumentopagoListDocumentopago.getDocumentopagoList().remove(documentopagoListDocumentopago);
                    oldCedulaOfDocumentopagoListDocumentopago = em.merge(oldCedulaOfDocumentopagoListDocumentopago);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getCedula()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getCedula());
            List<Documentopago> documentopagoListOld = persistentCliente.getDocumentopagoList();
            List<Documentopago> documentopagoListNew = cliente.getDocumentopagoList();
            List<String> illegalOrphanMessages = null;
            for (Documentopago documentopagoListOldDocumentopago : documentopagoListOld) {
                if (!documentopagoListNew.contains(documentopagoListOldDocumentopago)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Documentopago " + documentopagoListOldDocumentopago + " since its cedula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Documentopago> attachedDocumentopagoListNew = new ArrayList<Documentopago>();
            for (Documentopago documentopagoListNewDocumentopagoToAttach : documentopagoListNew) {
                documentopagoListNewDocumentopagoToAttach = em.getReference(documentopagoListNewDocumentopagoToAttach.getClass(), documentopagoListNewDocumentopagoToAttach.getIdDocumento());
                attachedDocumentopagoListNew.add(documentopagoListNewDocumentopagoToAttach);
            }
            documentopagoListNew = attachedDocumentopagoListNew;
            cliente.setDocumentopagoList(documentopagoListNew);
            cliente = em.merge(cliente);
            for (Documentopago documentopagoListNewDocumentopago : documentopagoListNew) {
                if (!documentopagoListOld.contains(documentopagoListNewDocumentopago)) {
                    Cliente oldCedulaOfDocumentopagoListNewDocumentopago = documentopagoListNewDocumentopago.getCedula();
                    documentopagoListNewDocumentopago.setCedula(cliente);
                    documentopagoListNewDocumentopago = em.merge(documentopagoListNewDocumentopago);
                    if (oldCedulaOfDocumentopagoListNewDocumentopago != null && !oldCedulaOfDocumentopagoListNewDocumentopago.equals(cliente)) {
                        oldCedulaOfDocumentopagoListNewDocumentopago.getDocumentopagoList().remove(documentopagoListNewDocumentopago);
                        oldCedulaOfDocumentopagoListNewDocumentopago = em.merge(oldCedulaOfDocumentopagoListNewDocumentopago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cliente.getCedula();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Documentopago> documentopagoListOrphanCheck = cliente.getDocumentopagoList();
            for (Documentopago documentopagoListOrphanCheckDocumentopago : documentopagoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Documentopago " + documentopagoListOrphanCheckDocumentopago + " in its documentopagoList field has a non-nullable cedula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
