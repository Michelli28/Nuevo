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
import model.entities.Movimientoalmacen;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.controllers.exceptions.NonexistentEntityException;
import model.entities.Tipoitem;

/**
 *
 * @author Administrador
 */
public class TipoitemJpaController implements Serializable {

    public TipoitemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipoitem tipoitem) {
        if (tipoitem.getMovimientoalmacenList() == null) {
            tipoitem.setMovimientoalmacenList(new ArrayList<Movimientoalmacen>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Movimientoalmacen> attachedMovimientoalmacenList = new ArrayList<Movimientoalmacen>();
            for (Movimientoalmacen movimientoalmacenListMovimientoalmacenToAttach : tipoitem.getMovimientoalmacenList()) {
                movimientoalmacenListMovimientoalmacenToAttach = em.getReference(movimientoalmacenListMovimientoalmacenToAttach.getClass(), movimientoalmacenListMovimientoalmacenToAttach.getIdMovimiento());
                attachedMovimientoalmacenList.add(movimientoalmacenListMovimientoalmacenToAttach);
            }
            tipoitem.setMovimientoalmacenList(attachedMovimientoalmacenList);
            em.persist(tipoitem);
            for (Movimientoalmacen movimientoalmacenListMovimientoalmacen : tipoitem.getMovimientoalmacenList()) {
                Tipoitem oldIdTipoItemOfMovimientoalmacenListMovimientoalmacen = movimientoalmacenListMovimientoalmacen.getIdTipoItem();
                movimientoalmacenListMovimientoalmacen.setIdTipoItem(tipoitem);
                movimientoalmacenListMovimientoalmacen = em.merge(movimientoalmacenListMovimientoalmacen);
                if (oldIdTipoItemOfMovimientoalmacenListMovimientoalmacen != null) {
                    oldIdTipoItemOfMovimientoalmacenListMovimientoalmacen.getMovimientoalmacenList().remove(movimientoalmacenListMovimientoalmacen);
                    oldIdTipoItemOfMovimientoalmacenListMovimientoalmacen = em.merge(oldIdTipoItemOfMovimientoalmacenListMovimientoalmacen);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipoitem tipoitem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipoitem persistentTipoitem = em.find(Tipoitem.class, tipoitem.getIdTipoItem());
            List<Movimientoalmacen> movimientoalmacenListOld = persistentTipoitem.getMovimientoalmacenList();
            List<Movimientoalmacen> movimientoalmacenListNew = tipoitem.getMovimientoalmacenList();
            List<Movimientoalmacen> attachedMovimientoalmacenListNew = new ArrayList<Movimientoalmacen>();
            for (Movimientoalmacen movimientoalmacenListNewMovimientoalmacenToAttach : movimientoalmacenListNew) {
                movimientoalmacenListNewMovimientoalmacenToAttach = em.getReference(movimientoalmacenListNewMovimientoalmacenToAttach.getClass(), movimientoalmacenListNewMovimientoalmacenToAttach.getIdMovimiento());
                attachedMovimientoalmacenListNew.add(movimientoalmacenListNewMovimientoalmacenToAttach);
            }
            movimientoalmacenListNew = attachedMovimientoalmacenListNew;
            tipoitem.setMovimientoalmacenList(movimientoalmacenListNew);
            tipoitem = em.merge(tipoitem);
            for (Movimientoalmacen movimientoalmacenListOldMovimientoalmacen : movimientoalmacenListOld) {
                if (!movimientoalmacenListNew.contains(movimientoalmacenListOldMovimientoalmacen)) {
                    movimientoalmacenListOldMovimientoalmacen.setIdTipoItem(null);
                    movimientoalmacenListOldMovimientoalmacen = em.merge(movimientoalmacenListOldMovimientoalmacen);
                }
            }
            for (Movimientoalmacen movimientoalmacenListNewMovimientoalmacen : movimientoalmacenListNew) {
                if (!movimientoalmacenListOld.contains(movimientoalmacenListNewMovimientoalmacen)) {
                    Tipoitem oldIdTipoItemOfMovimientoalmacenListNewMovimientoalmacen = movimientoalmacenListNewMovimientoalmacen.getIdTipoItem();
                    movimientoalmacenListNewMovimientoalmacen.setIdTipoItem(tipoitem);
                    movimientoalmacenListNewMovimientoalmacen = em.merge(movimientoalmacenListNewMovimientoalmacen);
                    if (oldIdTipoItemOfMovimientoalmacenListNewMovimientoalmacen != null && !oldIdTipoItemOfMovimientoalmacenListNewMovimientoalmacen.equals(tipoitem)) {
                        oldIdTipoItemOfMovimientoalmacenListNewMovimientoalmacen.getMovimientoalmacenList().remove(movimientoalmacenListNewMovimientoalmacen);
                        oldIdTipoItemOfMovimientoalmacenListNewMovimientoalmacen = em.merge(oldIdTipoItemOfMovimientoalmacenListNewMovimientoalmacen);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoitem.getIdTipoItem();
                if (findTipoitem(id) == null) {
                    throw new NonexistentEntityException("The tipoitem with id " + id + " no longer exists.");
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
            Tipoitem tipoitem;
            try {
                tipoitem = em.getReference(Tipoitem.class, id);
                tipoitem.getIdTipoItem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoitem with id " + id + " no longer exists.", enfe);
            }
            List<Movimientoalmacen> movimientoalmacenList = tipoitem.getMovimientoalmacenList();
            for (Movimientoalmacen movimientoalmacenListMovimientoalmacen : movimientoalmacenList) {
                movimientoalmacenListMovimientoalmacen.setIdTipoItem(null);
                movimientoalmacenListMovimientoalmacen = em.merge(movimientoalmacenListMovimientoalmacen);
            }
            em.remove(tipoitem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipoitem> findTipoitemEntities() {
        return findTipoitemEntities(true, -1, -1);
    }

    public List<Tipoitem> findTipoitemEntities(int maxResults, int firstResult) {
        return findTipoitemEntities(false, maxResults, firstResult);
    }

    private List<Tipoitem> findTipoitemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipoitem.class));
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

    public Tipoitem findTipoitem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipoitem.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoitemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipoitem> rt = cq.from(Tipoitem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
