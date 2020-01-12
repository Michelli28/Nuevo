/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controllers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entities.Fichatecnica;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Tipotela;

/**
 *
 * @author CHELLI BONITA
 */
public class TipotelaJpaController implements Serializable {

    public TipotelaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipotela tipotela) {
        if (tipotela.getFichatecnicaList() == null) {
            tipotela.setFichatecnicaList(new ArrayList<Fichatecnica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Fichatecnica> attachedFichatecnicaList = new ArrayList<Fichatecnica>();
            for (Fichatecnica fichatecnicaListFichatecnicaToAttach : tipotela.getFichatecnicaList()) {
                fichatecnicaListFichatecnicaToAttach = em.getReference(fichatecnicaListFichatecnicaToAttach.getClass(), fichatecnicaListFichatecnicaToAttach.getIdFicha());
                attachedFichatecnicaList.add(fichatecnicaListFichatecnicaToAttach);
            }
            tipotela.setFichatecnicaList(attachedFichatecnicaList);
            em.persist(tipotela);
            for (Fichatecnica fichatecnicaListFichatecnica : tipotela.getFichatecnicaList()) {
                Tipotela oldIdTipoOfFichatecnicaListFichatecnica = fichatecnicaListFichatecnica.getIdTipo();
                fichatecnicaListFichatecnica.setIdTipo(tipotela);
                fichatecnicaListFichatecnica = em.merge(fichatecnicaListFichatecnica);
                if (oldIdTipoOfFichatecnicaListFichatecnica != null) {
                    oldIdTipoOfFichatecnicaListFichatecnica.getFichatecnicaList().remove(fichatecnicaListFichatecnica);
                    oldIdTipoOfFichatecnicaListFichatecnica = em.merge(oldIdTipoOfFichatecnicaListFichatecnica);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipotela tipotela) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipotela persistentTipotela = em.find(Tipotela.class, tipotela.getIdTipo());
            List<Fichatecnica> fichatecnicaListOld = persistentTipotela.getFichatecnicaList();
            List<Fichatecnica> fichatecnicaListNew = tipotela.getFichatecnicaList();
            List<Fichatecnica> attachedFichatecnicaListNew = new ArrayList<Fichatecnica>();
            for (Fichatecnica fichatecnicaListNewFichatecnicaToAttach : fichatecnicaListNew) {
                fichatecnicaListNewFichatecnicaToAttach = em.getReference(fichatecnicaListNewFichatecnicaToAttach.getClass(), fichatecnicaListNewFichatecnicaToAttach.getIdFicha());
                attachedFichatecnicaListNew.add(fichatecnicaListNewFichatecnicaToAttach);
            }
            fichatecnicaListNew = attachedFichatecnicaListNew;
            tipotela.setFichatecnicaList(fichatecnicaListNew);
            tipotela = em.merge(tipotela);
            for (Fichatecnica fichatecnicaListOldFichatecnica : fichatecnicaListOld) {
                if (!fichatecnicaListNew.contains(fichatecnicaListOldFichatecnica)) {
                    fichatecnicaListOldFichatecnica.setIdTipo(null);
                    fichatecnicaListOldFichatecnica = em.merge(fichatecnicaListOldFichatecnica);
                }
            }
            for (Fichatecnica fichatecnicaListNewFichatecnica : fichatecnicaListNew) {
                if (!fichatecnicaListOld.contains(fichatecnicaListNewFichatecnica)) {
                    Tipotela oldIdTipoOfFichatecnicaListNewFichatecnica = fichatecnicaListNewFichatecnica.getIdTipo();
                    fichatecnicaListNewFichatecnica.setIdTipo(tipotela);
                    fichatecnicaListNewFichatecnica = em.merge(fichatecnicaListNewFichatecnica);
                    if (oldIdTipoOfFichatecnicaListNewFichatecnica != null && !oldIdTipoOfFichatecnicaListNewFichatecnica.equals(tipotela)) {
                        oldIdTipoOfFichatecnicaListNewFichatecnica.getFichatecnicaList().remove(fichatecnicaListNewFichatecnica);
                        oldIdTipoOfFichatecnicaListNewFichatecnica = em.merge(oldIdTipoOfFichatecnicaListNewFichatecnica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipotela.getIdTipo();
                if (findTipotela(id) == null) {
                    throw new NonexistentEntityException("The tipotela with id " + id + " no longer exists.");
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
            Tipotela tipotela;
            try {
                tipotela = em.getReference(Tipotela.class, id);
                tipotela.getIdTipo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipotela with id " + id + " no longer exists.", enfe);
            }
            List<Fichatecnica> fichatecnicaList = tipotela.getFichatecnicaList();
            for (Fichatecnica fichatecnicaListFichatecnica : fichatecnicaList) {
                fichatecnicaListFichatecnica.setIdTipo(null);
                fichatecnicaListFichatecnica = em.merge(fichatecnicaListFichatecnica);
            }
            em.remove(tipotela);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipotela> findTipotelaEntities() {
        return findTipotelaEntities(true, -1, -1);
    }

    public List<Tipotela> findTipotelaEntities(int maxResults, int firstResult) {
        return findTipotelaEntities(false, maxResults, firstResult);
    }

    private List<Tipotela> findTipotelaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipotela.class));
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

    public Tipotela findTipotela(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipotela.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipotelaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipotela> rt = cq.from(Tipotela.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
