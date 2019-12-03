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
import DAO.Facturacion.Documentopago;
import DAO.Facturacion.Tienda;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Santiago
 */
public class TiendaJpaController implements Serializable {

    public TiendaJpaController( ) {
        this.emf = Persistence.createEntityManagerFactory("SistemaFacturacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tienda tienda) throws PreexistingEntityException, Exception {
        if (tienda.getDocumentopagoList() == null) {
            tienda.setDocumentopagoList(new ArrayList<Documentopago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Documentopago> attachedDocumentopagoList = new ArrayList<Documentopago>();
            for (Documentopago documentopagoListDocumentopagoToAttach : tienda.getDocumentopagoList()) {
                documentopagoListDocumentopagoToAttach = em.getReference(documentopagoListDocumentopagoToAttach.getClass(), documentopagoListDocumentopagoToAttach.getIdDocumento());
                attachedDocumentopagoList.add(documentopagoListDocumentopagoToAttach);
            }
            tienda.setDocumentopagoList(attachedDocumentopagoList);
            em.persist(tienda);
            for (Documentopago documentopagoListDocumentopago : tienda.getDocumentopagoList()) {
                Tienda oldIdTiendaOfDocumentopagoListDocumentopago = documentopagoListDocumentopago.getIdTienda();
                documentopagoListDocumentopago.setIdTienda(tienda);
                documentopagoListDocumentopago = em.merge(documentopagoListDocumentopago);
                if (oldIdTiendaOfDocumentopagoListDocumentopago != null) {
                    oldIdTiendaOfDocumentopagoListDocumentopago.getDocumentopagoList().remove(documentopagoListDocumentopago);
                    oldIdTiendaOfDocumentopagoListDocumentopago = em.merge(oldIdTiendaOfDocumentopagoListDocumentopago);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTienda(tienda.getIdTienda()) != null) {
                throw new PreexistingEntityException("Tienda " + tienda + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tienda tienda) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tienda persistentTienda = em.find(Tienda.class, tienda.getIdTienda());
            List<Documentopago> documentopagoListOld = persistentTienda.getDocumentopagoList();
            List<Documentopago> documentopagoListNew = tienda.getDocumentopagoList();
            List<String> illegalOrphanMessages = null;
            for (Documentopago documentopagoListOldDocumentopago : documentopagoListOld) {
                if (!documentopagoListNew.contains(documentopagoListOldDocumentopago)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Documentopago " + documentopagoListOldDocumentopago + " since its idTienda field is not nullable.");
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
            tienda.setDocumentopagoList(documentopagoListNew);
            tienda = em.merge(tienda);
            for (Documentopago documentopagoListNewDocumentopago : documentopagoListNew) {
                if (!documentopagoListOld.contains(documentopagoListNewDocumentopago)) {
                    Tienda oldIdTiendaOfDocumentopagoListNewDocumentopago = documentopagoListNewDocumentopago.getIdTienda();
                    documentopagoListNewDocumentopago.setIdTienda(tienda);
                    documentopagoListNewDocumentopago = em.merge(documentopagoListNewDocumentopago);
                    if (oldIdTiendaOfDocumentopagoListNewDocumentopago != null && !oldIdTiendaOfDocumentopagoListNewDocumentopago.equals(tienda)) {
                        oldIdTiendaOfDocumentopagoListNewDocumentopago.getDocumentopagoList().remove(documentopagoListNewDocumentopago);
                        oldIdTiendaOfDocumentopagoListNewDocumentopago = em.merge(oldIdTiendaOfDocumentopagoListNewDocumentopago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tienda.getIdTienda();
                if (findTienda(id) == null) {
                    throw new NonexistentEntityException("The tienda with id " + id + " no longer exists.");
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
            Tienda tienda;
            try {
                tienda = em.getReference(Tienda.class, id);
                tienda.getIdTienda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tienda with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Documentopago> documentopagoListOrphanCheck = tienda.getDocumentopagoList();
            for (Documentopago documentopagoListOrphanCheckDocumentopago : documentopagoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tienda (" + tienda + ") cannot be destroyed since the Documentopago " + documentopagoListOrphanCheckDocumentopago + " in its documentopagoList field has a non-nullable idTienda field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tienda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tienda> findTiendaEntities() {
        return findTiendaEntities(true, -1, -1);
    }

    public List<Tienda> findTiendaEntities(int maxResults, int firstResult) {
        return findTiendaEntities(false, maxResults, firstResult);
    }

    private List<Tienda> findTiendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tienda.class));
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

    public Tienda findTienda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tienda.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tienda> rt = cq.from(Tienda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
