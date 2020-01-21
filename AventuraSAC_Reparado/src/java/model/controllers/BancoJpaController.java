
package model.controllers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entities.Pagos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Banco;

/**
 *
 * @author Administrador
 */
public class BancoJpaController implements Serializable {

    public BancoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Banco banco) {
        if (banco.getPagosList() == null) {
            banco.setPagosList(new ArrayList<Pagos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pagos> attachedPagosList = new ArrayList<Pagos>();
            
           
            for (Pagos pagosListPagosToAttach : banco.getPagosList()) {
                
                pagosListPagosToAttach = em.getReference(pagosListPagosToAttach.getClass(), pagosListPagosToAttach.getNumeroOperacion());
                attachedPagosList.add(pagosListPagosToAttach);
            }
            banco.setPagosList(attachedPagosList);
            em.persist(banco);
            for (Pagos pagosListPagos : banco.getPagosList()) {
                Banco oldIdBancoOfPagosListPagos = pagosListPagos.getIdBanco();
                pagosListPagos.setIdBanco(banco);
                pagosListPagos = em.merge(pagosListPagos);
                if (oldIdBancoOfPagosListPagos != null) {
                    oldIdBancoOfPagosListPagos.getPagosList().remove(pagosListPagos);
                    oldIdBancoOfPagosListPagos = em.merge(oldIdBancoOfPagosListPagos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Banco banco) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Banco persistentBanco = em.find(Banco.class, banco.getIdBanco());
            List<Pagos> pagosListOld = persistentBanco.getPagosList();
            List<Pagos> pagosListNew = banco.getPagosList();
            List<Pagos> attachedPagosListNew = new ArrayList<Pagos>();
            for (Pagos pagosListNewPagosToAttach : pagosListNew) {
                pagosListNewPagosToAttach = em.getReference(pagosListNewPagosToAttach.getClass(), pagosListNewPagosToAttach.getNumeroOperacion());
                attachedPagosListNew.add(pagosListNewPagosToAttach);
            }
            pagosListNew = attachedPagosListNew;
            banco.setPagosList(pagosListNew);
            banco = em.merge(banco);
            for (Pagos pagosListOldPagos : pagosListOld) {
                if (!pagosListNew.contains(pagosListOldPagos)) {
                    pagosListOldPagos.setIdBanco(null);
                    pagosListOldPagos = em.merge(pagosListOldPagos);
                }
            }
            for (Pagos pagosListNewPagos : pagosListNew) {
                if (!pagosListOld.contains(pagosListNewPagos)) {
                    Banco oldIdBancoOfPagosListNewPagos = pagosListNewPagos.getIdBanco();
                    pagosListNewPagos.setIdBanco(banco);
                    pagosListNewPagos = em.merge(pagosListNewPagos);
                    if (oldIdBancoOfPagosListNewPagos != null && !oldIdBancoOfPagosListNewPagos.equals(banco)) {
                        oldIdBancoOfPagosListNewPagos.getPagosList().remove(pagosListNewPagos);
                        oldIdBancoOfPagosListNewPagos = em.merge(oldIdBancoOfPagosListNewPagos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = banco.getIdBanco();
                if (findBanco(id) == null) {
                    throw new NonexistentEntityException("The banco with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Banco banco;
            try {
                banco = em.getReference(Banco.class, id);
                banco.getIdBanco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The banco with id " + id + " no longer exists.", enfe);
            }
            List<Pagos> pagosList = banco.getPagosList();
            for (Pagos pagosListPagos : pagosList) {
                pagosListPagos.setIdBanco(null);
                pagosListPagos = em.merge(pagosListPagos);
            }
            em.remove(banco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Banco> findBancoEntities() {
        return findBancoEntities(true, -1, -1);
    }

    public List<Banco> findBancoEntities(int maxResults, int firstResult) {
        return findBancoEntities(false, maxResults, firstResult);
    }

    private List<Banco> findBancoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Banco.class));
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

    public Banco findBanco(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Banco.class, id);
        } finally {
            em.close();
        }
    }

    public int getBancoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Banco> rt = cq.from(Banco.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
