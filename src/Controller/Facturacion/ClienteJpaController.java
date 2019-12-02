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
import DAO.Facturacion.Ruc;
import java.util.ArrayList;
import java.util.List;
import DAO.Facturacion.Cedula;
import DAO.Facturacion.Cliente;
import DAO.Facturacion.Documentopago;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Santiago
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getRucList() == null) {
            cliente.setRucList(new ArrayList<Ruc>());
        }
        if (cliente.getCedulaList() == null) {
            cliente.setCedulaList(new ArrayList<Cedula>());
        }
        if (cliente.getDocumentopagoList() == null) {
            cliente.setDocumentopagoList(new ArrayList<Documentopago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ruc> attachedRucList = new ArrayList<Ruc>();
            for (Ruc rucListRucToAttach : cliente.getRucList()) {
                rucListRucToAttach = em.getReference(rucListRucToAttach.getClass(), rucListRucToAttach.getRuc());
                attachedRucList.add(rucListRucToAttach);
            }
            cliente.setRucList(attachedRucList);
            List<Cedula> attachedCedulaList = new ArrayList<Cedula>();
            for (Cedula cedulaListCedulaToAttach : cliente.getCedulaList()) {
                cedulaListCedulaToAttach = em.getReference(cedulaListCedulaToAttach.getClass(), cedulaListCedulaToAttach.getCedula());
                attachedCedulaList.add(cedulaListCedulaToAttach);
            }
            cliente.setCedulaList(attachedCedulaList);
            List<Documentopago> attachedDocumentopagoList = new ArrayList<Documentopago>();
            for (Documentopago documentopagoListDocumentopagoToAttach : cliente.getDocumentopagoList()) {
                documentopagoListDocumentopagoToAttach = em.getReference(documentopagoListDocumentopagoToAttach.getClass(), documentopagoListDocumentopagoToAttach.getIdDocumento());
                attachedDocumentopagoList.add(documentopagoListDocumentopagoToAttach);
            }
            cliente.setDocumentopagoList(attachedDocumentopagoList);
            em.persist(cliente);
            for (Ruc rucListRuc : cliente.getRucList()) {
                Cliente oldIdClienteOfRucListRuc = rucListRuc.getIdCliente();
                rucListRuc.setIdCliente(cliente);
                rucListRuc = em.merge(rucListRuc);
                if (oldIdClienteOfRucListRuc != null) {
                    oldIdClienteOfRucListRuc.getRucList().remove(rucListRuc);
                    oldIdClienteOfRucListRuc = em.merge(oldIdClienteOfRucListRuc);
                }
            }
            for (Cedula cedulaListCedula : cliente.getCedulaList()) {
                Cliente oldIdClienteOfCedulaListCedula = cedulaListCedula.getIdCliente();
                cedulaListCedula.setIdCliente(cliente);
                cedulaListCedula = em.merge(cedulaListCedula);
                if (oldIdClienteOfCedulaListCedula != null) {
                    oldIdClienteOfCedulaListCedula.getCedulaList().remove(cedulaListCedula);
                    oldIdClienteOfCedulaListCedula = em.merge(oldIdClienteOfCedulaListCedula);
                }
            }
            for (Documentopago documentopagoListDocumentopago : cliente.getDocumentopagoList()) {
                Cliente oldIdClienteOfDocumentopagoListDocumentopago = documentopagoListDocumentopago.getIdCliente();
                documentopagoListDocumentopago.setIdCliente(cliente);
                documentopagoListDocumentopago = em.merge(documentopagoListDocumentopago);
                if (oldIdClienteOfDocumentopagoListDocumentopago != null) {
                    oldIdClienteOfDocumentopagoListDocumentopago.getDocumentopagoList().remove(documentopagoListDocumentopago);
                    oldIdClienteOfDocumentopagoListDocumentopago = em.merge(oldIdClienteOfDocumentopagoListDocumentopago);
                }
            }
            em.getTransaction().commit();
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
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            List<Ruc> rucListOld = persistentCliente.getRucList();
            List<Ruc> rucListNew = cliente.getRucList();
            List<Cedula> cedulaListOld = persistentCliente.getCedulaList();
            List<Cedula> cedulaListNew = cliente.getCedulaList();
            List<Documentopago> documentopagoListOld = persistentCliente.getDocumentopagoList();
            List<Documentopago> documentopagoListNew = cliente.getDocumentopagoList();
            List<String> illegalOrphanMessages = null;
            for (Ruc rucListOldRuc : rucListOld) {
                if (!rucListNew.contains(rucListOldRuc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ruc " + rucListOldRuc + " since its idCliente field is not nullable.");
                }
            }
            for (Cedula cedulaListOldCedula : cedulaListOld) {
                if (!cedulaListNew.contains(cedulaListOldCedula)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cedula " + cedulaListOldCedula + " since its idCliente field is not nullable.");
                }
            }
            for (Documentopago documentopagoListOldDocumentopago : documentopagoListOld) {
                if (!documentopagoListNew.contains(documentopagoListOldDocumentopago)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Documentopago " + documentopagoListOldDocumentopago + " since its idCliente field is not nullable.");
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
            cliente.setRucList(rucListNew);
            List<Cedula> attachedCedulaListNew = new ArrayList<Cedula>();
            for (Cedula cedulaListNewCedulaToAttach : cedulaListNew) {
                cedulaListNewCedulaToAttach = em.getReference(cedulaListNewCedulaToAttach.getClass(), cedulaListNewCedulaToAttach.getCedula());
                attachedCedulaListNew.add(cedulaListNewCedulaToAttach);
            }
            cedulaListNew = attachedCedulaListNew;
            cliente.setCedulaList(cedulaListNew);
            List<Documentopago> attachedDocumentopagoListNew = new ArrayList<Documentopago>();
            for (Documentopago documentopagoListNewDocumentopagoToAttach : documentopagoListNew) {
                documentopagoListNewDocumentopagoToAttach = em.getReference(documentopagoListNewDocumentopagoToAttach.getClass(), documentopagoListNewDocumentopagoToAttach.getIdDocumento());
                attachedDocumentopagoListNew.add(documentopagoListNewDocumentopagoToAttach);
            }
            documentopagoListNew = attachedDocumentopagoListNew;
            cliente.setDocumentopagoList(documentopagoListNew);
            cliente = em.merge(cliente);
            for (Ruc rucListNewRuc : rucListNew) {
                if (!rucListOld.contains(rucListNewRuc)) {
                    Cliente oldIdClienteOfRucListNewRuc = rucListNewRuc.getIdCliente();
                    rucListNewRuc.setIdCliente(cliente);
                    rucListNewRuc = em.merge(rucListNewRuc);
                    if (oldIdClienteOfRucListNewRuc != null && !oldIdClienteOfRucListNewRuc.equals(cliente)) {
                        oldIdClienteOfRucListNewRuc.getRucList().remove(rucListNewRuc);
                        oldIdClienteOfRucListNewRuc = em.merge(oldIdClienteOfRucListNewRuc);
                    }
                }
            }
            for (Cedula cedulaListNewCedula : cedulaListNew) {
                if (!cedulaListOld.contains(cedulaListNewCedula)) {
                    Cliente oldIdClienteOfCedulaListNewCedula = cedulaListNewCedula.getIdCliente();
                    cedulaListNewCedula.setIdCliente(cliente);
                    cedulaListNewCedula = em.merge(cedulaListNewCedula);
                    if (oldIdClienteOfCedulaListNewCedula != null && !oldIdClienteOfCedulaListNewCedula.equals(cliente)) {
                        oldIdClienteOfCedulaListNewCedula.getCedulaList().remove(cedulaListNewCedula);
                        oldIdClienteOfCedulaListNewCedula = em.merge(oldIdClienteOfCedulaListNewCedula);
                    }
                }
            }
            for (Documentopago documentopagoListNewDocumentopago : documentopagoListNew) {
                if (!documentopagoListOld.contains(documentopagoListNewDocumentopago)) {
                    Cliente oldIdClienteOfDocumentopagoListNewDocumentopago = documentopagoListNewDocumentopago.getIdCliente();
                    documentopagoListNewDocumentopago.setIdCliente(cliente);
                    documentopagoListNewDocumentopago = em.merge(documentopagoListNewDocumentopago);
                    if (oldIdClienteOfDocumentopagoListNewDocumentopago != null && !oldIdClienteOfDocumentopagoListNewDocumentopago.equals(cliente)) {
                        oldIdClienteOfDocumentopagoListNewDocumentopago.getDocumentopagoList().remove(documentopagoListNewDocumentopago);
                        oldIdClienteOfDocumentopagoListNewDocumentopago = em.merge(oldIdClienteOfDocumentopagoListNewDocumentopago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdCliente();
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ruc> rucListOrphanCheck = cliente.getRucList();
            for (Ruc rucListOrphanCheckRuc : rucListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Ruc " + rucListOrphanCheckRuc + " in its rucList field has a non-nullable idCliente field.");
            }
            List<Cedula> cedulaListOrphanCheck = cliente.getCedulaList();
            for (Cedula cedulaListOrphanCheckCedula : cedulaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Cedula " + cedulaListOrphanCheckCedula + " in its cedulaList field has a non-nullable idCliente field.");
            }
            List<Documentopago> documentopagoListOrphanCheck = cliente.getDocumentopagoList();
            for (Documentopago documentopagoListOrphanCheckDocumentopago : documentopagoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Documentopago " + documentopagoListOrphanCheckDocumentopago + " in its documentopagoList field has a non-nullable idCliente field.");
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

    public Cliente findCliente(Integer id) {
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
