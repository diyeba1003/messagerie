/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Liste;
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
public class ListeDao implements ListeDaoInterface {

    @PersistenceContext
    private transient EntityManager em;

    @Override
    public Liste findListeById(String idlist) {
        Liste liste = em.find(Liste.class, idlist);
        return liste;
    }

    @Override
    public Integer countListe() {
        Integer count = 0;
        try {
            Object object = em.createQuery("SELECT COUNT(l) FROM Liste l").getSingleResult();
            if (object != null) {
                count = (int) (long) object;
            }
        } catch (NoResultException e) {
            count = 0;
        }
        return count;
    }

    @Override
    public void updateList(Liste liste) {
        em.merge(liste);
    }

    @Override
    public void deleteListe(Liste liste) {
        if (liste != null) {
            em.remove(liste);
            System.out.println(">> Success remove Liste !!!");
            em.flush();
        }
    }

}
