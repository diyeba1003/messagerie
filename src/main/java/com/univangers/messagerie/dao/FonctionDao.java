/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Fonction;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author etud
 */
@Repository
@Transactional
public class FonctionDao implements FonctionDaoInterface {

    @PersistenceContext
    private transient EntityManager em;

    @Override
    public void insertFonction(Fonction idFONCTION) {
        em.persist(idFONCTION);
        em.flush();
    }

    @Override
    public Fonction findFonctionById(Integer idFonction) {
        Fonction f;
        try {
            f = em.find(Fonction.class, idFonction);
        } catch (NoResultException nre) {
            f = null;
        }
        return f;
    }

    @Override
    public List<Fonction> findAllFonction() {
        return null;
    }

    @Override
    public Fonction findFonctionByTitle(String title) {
        Fonction f;
        try {
            f = (Fonction) em.createQuery("SELECT f FROM Fonction f WHERE f.title = :title")
                    .setParameter("title", title)
                    .getSingleResult();

        } catch (NoResultException nre) {
            f = null;
        }
        return f;

    }

}
