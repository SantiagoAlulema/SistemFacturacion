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
import DAO.Facturacion.Rol;
import DAO.Facturacion.Documentopago;
import DAO.Facturacion.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Santiago
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SistemaFacturacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getDocumentopagoList() == null) {
            usuario.setDocumentopagoList(new ArrayList<Documentopago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol idRol = usuario.getIdRol();
            if (idRol != null) {
                idRol = em.getReference(idRol.getClass(), idRol.getIdRol());
                usuario.setIdRol(idRol);
            }
            List<Documentopago> attachedDocumentopagoList = new ArrayList<Documentopago>();
            for (Documentopago documentopagoListDocumentopagoToAttach : usuario.getDocumentopagoList()) {
                documentopagoListDocumentopagoToAttach = em.getReference(documentopagoListDocumentopagoToAttach.getClass(), documentopagoListDocumentopagoToAttach.getIdDocumento());
                attachedDocumentopagoList.add(documentopagoListDocumentopagoToAttach);
            }
            usuario.setDocumentopagoList(attachedDocumentopagoList);
            em.persist(usuario);
            if (idRol != null) {
                idRol.getUsuarioList().add(usuario);
                idRol = em.merge(idRol);
            }
            for (Documentopago documentopagoListDocumentopago : usuario.getDocumentopagoList()) {
                Usuario oldUsuarioCedulaOfDocumentopagoListDocumentopago = documentopagoListDocumentopago.getUsuarioCedula();
                documentopagoListDocumentopago.setUsuarioCedula(usuario);
                documentopagoListDocumentopago = em.merge(documentopagoListDocumentopago);
                if (oldUsuarioCedulaOfDocumentopagoListDocumentopago != null) {
                    oldUsuarioCedulaOfDocumentopagoListDocumentopago.getDocumentopagoList().remove(documentopagoListDocumentopago);
                    oldUsuarioCedulaOfDocumentopagoListDocumentopago = em.merge(oldUsuarioCedulaOfDocumentopagoListDocumentopago);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getCedula()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getCedula());
            Rol idRolOld = persistentUsuario.getIdRol();
            Rol idRolNew = usuario.getIdRol();
            List<Documentopago> documentopagoListOld = persistentUsuario.getDocumentopagoList();
            List<Documentopago> documentopagoListNew = usuario.getDocumentopagoList();
            List<String> illegalOrphanMessages = null;
            for (Documentopago documentopagoListOldDocumentopago : documentopagoListOld) {
                if (!documentopagoListNew.contains(documentopagoListOldDocumentopago)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Documentopago " + documentopagoListOldDocumentopago + " since its usuarioCedula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idRolNew != null) {
                idRolNew = em.getReference(idRolNew.getClass(), idRolNew.getIdRol());
                usuario.setIdRol(idRolNew);
            }
            List<Documentopago> attachedDocumentopagoListNew = new ArrayList<Documentopago>();
            for (Documentopago documentopagoListNewDocumentopagoToAttach : documentopagoListNew) {
                documentopagoListNewDocumentopagoToAttach = em.getReference(documentopagoListNewDocumentopagoToAttach.getClass(), documentopagoListNewDocumentopagoToAttach.getIdDocumento());
                attachedDocumentopagoListNew.add(documentopagoListNewDocumentopagoToAttach);
            }
            documentopagoListNew = attachedDocumentopagoListNew;
            usuario.setDocumentopagoList(documentopagoListNew);
            usuario = em.merge(usuario);
            if (idRolOld != null && !idRolOld.equals(idRolNew)) {
                idRolOld.getUsuarioList().remove(usuario);
                idRolOld = em.merge(idRolOld);
            }
            if (idRolNew != null && !idRolNew.equals(idRolOld)) {
                idRolNew.getUsuarioList().add(usuario);
                idRolNew = em.merge(idRolNew);
            }
            for (Documentopago documentopagoListNewDocumentopago : documentopagoListNew) {
                if (!documentopagoListOld.contains(documentopagoListNewDocumentopago)) {
                    Usuario oldUsuarioCedulaOfDocumentopagoListNewDocumentopago = documentopagoListNewDocumentopago.getUsuarioCedula();
                    documentopagoListNewDocumentopago.setUsuarioCedula(usuario);
                    documentopagoListNewDocumentopago = em.merge(documentopagoListNewDocumentopago);
                    if (oldUsuarioCedulaOfDocumentopagoListNewDocumentopago != null && !oldUsuarioCedulaOfDocumentopagoListNewDocumentopago.equals(usuario)) {
                        oldUsuarioCedulaOfDocumentopagoListNewDocumentopago.getDocumentopagoList().remove(documentopagoListNewDocumentopago);
                        oldUsuarioCedulaOfDocumentopagoListNewDocumentopago = em.merge(oldUsuarioCedulaOfDocumentopagoListNewDocumentopago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getCedula();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Documentopago> documentopagoListOrphanCheck = usuario.getDocumentopagoList();
            for (Documentopago documentopagoListOrphanCheckDocumentopago : documentopagoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Documentopago " + documentopagoListOrphanCheckDocumentopago + " in its documentopagoList field has a non-nullable usuarioCedula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Rol idRol = usuario.getIdRol();
            if (idRol != null) {
                idRol.getUsuarioList().remove(usuario);
                idRol = em.merge(idRol);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
