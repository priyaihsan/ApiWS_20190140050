/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.getmymessage;

import com.example.getmymessage.exceptions.NonexistentEntityException;
import com.example.getmymessage.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Ican
 */
public class MymessageJpaController implements Serializable {

    public MymessageJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.example_getmymessage_jar_0.0.1-SNAPSHOTPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MymessageJpaController() {
        
    }
    
    public void create(Mymessage mymessage) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(mymessage);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMymessage(mymessage.getIdMessage()) != null) {
                throw new PreexistingEntityException("Mymessage " + mymessage + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mymessage mymessage) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mymessage = em.merge(mymessage);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mymessage.getIdMessage();
                if (findMymessage(id) == null) {
                    throw new NonexistentEntityException("The mymessage with id " + id + " no longer exists.");
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
            Mymessage mymessage;
            try {
                mymessage = em.getReference(Mymessage.class, id);
                mymessage.getIdMessage();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mymessage with id " + id + " no longer exists.", enfe);
            }
            em.remove(mymessage);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mymessage> findMymessageEntities() {
        return findMymessageEntities(true, -1, -1);
    }

    public List<Mymessage> findMymessageEntities(int maxResults, int firstResult) {
        return findMymessageEntities(false, maxResults, firstResult);
    }

    private List<Mymessage> findMymessageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mymessage.class));
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

    public Mymessage findMymessage(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mymessage.class, id);
        } finally {
            em.close();
        }
    }

    public int getMymessageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mymessage> rt = cq.from(Mymessage.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
