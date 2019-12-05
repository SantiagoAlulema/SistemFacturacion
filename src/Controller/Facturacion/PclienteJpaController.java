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
import DAO.Facturacion.Pcliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Santiago
 */
public class PclienteJpaController implements Serializable {

    public PclienteJpaController( ) {
        this.emf = Persistence.createEntityManagerFactory("SistemaFacturacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pcliente pcliente) {
        if (pcliente.getRucList() == null) {
            pcliente.setRucList(new ArrayList<Ruc>());
        }
        if (pcliente.getCedulaList() == null) {
            pcliente.setCedulaList(new ArrayList<Cedula>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ruc> attachedRucList = new ArrayList<Ruc>();
            for (Ruc rucListRucToAttach : pcliente.getRucList()) {
                rucListRucToAttach = em.getReference(rucListRucToAttach.getClass(), rucListRucToAttach.getRuc());
                attachedRucList.add(rucListRucToAttach);
            }
            pcliente.setRucList(attachedRucList);
            List<Cedula> attachedCedulaList = new ArrayList<Cedula>();
            for (Cedula cedulaListCedulaToAttach : pcliente.getCedulaList()) {
                cedulaListCedulaToAttach = em.getReference(cedulaListCedulaToAttach.getClass(), cedulaListCedulaToAttach.getCedula());
                attachedCedulaList.add(cedulaListCedulaToAttach);
            }
            pcliente.setCedulaList(attachedCedulaList);
            em.persist(pcliente);
            for (Ruc rucListRuc : pcliente.getRucList()) {
                Pcliente oldIdPClienteOfRucListRuc = rucListRuc.getIdPCliente();
                rucListRuc.setIdPCliente(pcliente);
                rucListRuc = em.merge(rucListRuc);
                if (oldIdPClienteOfRucListRuc != null) {
                    oldIdPClienteOfRucListRuc.getRucList().remove(rucListRuc);
                    oldIdPClienteOfRucListRuc = em.merge(oldIdPClienteOfRucListRuc);
                }
            }
            for (Cedula cedulaListCedula : pcliente.getCedulaList()) {
                Pcliente oldIdPClienteOfCedulaListCedula = cedulaListCedula.getIdPCliente();
                cedulaListCedula.setIdPCliente(pcliente);
                cedulaListCedula = em.merge(cedulaListCedula);
                if (oldIdPClienteOfCedulaListCedula != null) {
                    oldIdPClienteOfCedulaListCedula.getCedulaList().remove(cedulaListCedula);
                    oldIdPClienteOfCedulaListCedula = em.merge(oldIdPClienteOfCedulaListCedula);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pcliente pcliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pcliente persistentPcliente = em.find(Pcliente.class, pcliente.getIdPCliente());
            List<Ruc> rucListOld = persistentPcliente.getRucList();
            List<Ruc> rucListNew = pcliente.getRucList();
            List<Cedula> cedulaListOld = persistentPcliente.getCedulaList();
            List<Cedula> cedulaListNew = pcliente.getCedulaList();
            List<String> illegalOrphanMessages = null;
            for (Ruc rucListOldRuc : rucListOld) {
                if (!rucListNew.contains(rucListOldRuc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ruc " + rucListOldRuc + " since its idPCliente field is not nullable.");
                }
            }
            for (Cedula cedulaListOldCedula : cedulaListOld) {
                if (!cedulaListNew.contains(cedulaListOldCedula)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cedula " + cedulaListOldCedula + " since its idPCliente field is not nullable.");
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
            pcliente.setRucList(rucListNew);
            List<Cedula> attachedCedulaListNew = new ArrayList<Cedula>();
            for (Cedula cedulaListNewCedulaToAttach : cedulaListNew) {
                cedulaListNewCedulaToAttach = em.getReference(cedulaListNewCedulaToAttach.getClass(), cedulaListNewCedulaToAttach.getCedula());
                attachedCedulaListNew.add(cedulaListNewCedulaToAttach);
            }
            cedulaListNew = attachedCedulaListNew;
            pcliente.setCedulaList(cedulaListNew);
            pcliente = em.merge(pcliente);
            for (Ruc rucListNewRuc : rucListNew) {
                if (!rucListOld.contains(rucListNewRuc)) {
                    Pcliente oldIdPClienteOfRucListNewRuc = rucListNewRuc.getIdPCliente();
                    rucListNewRuc.setIdPCliente(pcliente);
                    rucListNewRuc = em.merge(rucListNewRuc);
                    if (oldIdPClienteOfRucListNewRuc != null && !oldIdPClienteOfRucListNewRuc.equals(pcliente)) {
                        oldIdPClienteOfRucListNewRuc.getRucList().remove(rucListNewRuc);
                        oldIdPClienteOfRucListNewRuc = em.merge(oldIdPClienteOfRucListNewRuc);
                    }
                }
            }
            for (Cedula cedulaListNewCedula : cedulaListNew) {
                if (!cedulaListOld.contains(cedulaListNewCedula)) {
                    Pcliente oldIdPClienteOfCedulaListNewCedula = cedulaListNewCedula.getIdPCliente();
                    cedulaListNewCedula.setIdPCliente(pcliente);
                    cedulaListNewCedula = em.merge(cedulaListNewCedula);
                    if (oldIdPClienteOfCedulaListNewCedula != null && !oldIdPClienteOfCedulaListNewCedula.equals(pcliente)) {
                        oldIdPClienteOfCedulaListNewCedula.getCedulaList().remove(cedulaListNewCedula);
                        oldIdPClienteOfCedulaListNewCedula = em.merge(oldIdPClienteOfCedulaListNewCedula);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pcliente.getIdPCliente();
                if (findPcliente(id) == null) {
                    throw new NonexistentEntityException("The pcliente with id " + id + " no longer exists.");
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
            Pcliente pcliente;
            try {
                pcliente = em.getReference(Pcliente.class, id);
                pcliente.getIdPCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pcliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ruc> rucListOrphanCheck = pcliente.getRucList();
            for (Ruc rucListOrphanCheckRuc : rucListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pcliente (" + pcliente + ") cannot be destroyed since the Ruc " + rucListOrphanCheckRuc + " in its rucList field has a non-nullable idPCliente field.");
            }
            List<Cedula> cedulaListOrphanCheck = pcliente.getCedulaList();
            for (Cedula cedulaListOrphanCheckCedula : cedulaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pcliente (" + pcliente + ") cannot be destroyed since the Cedula " + cedulaListOrphanCheckCedula + " in its cedulaList field has a non-nullable idPCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pcliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pcliente> findPclienteEntities() {
        return findPclienteEntities(true, -1, -1);
    }

    public List<Pcliente> findPclienteEntities(int maxResults, int firstResult) {
        return findPclienteEntities(false, maxResults, firstResult);
    }

    private List<Pcliente> findPclienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pcliente.class));
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

    public Pcliente findPcliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pcliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPclienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pcliente> rt = cq.from(Pcliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
