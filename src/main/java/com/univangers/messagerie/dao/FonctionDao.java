/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Fonction;
import java.util.List;
import javax.persistence.EntityManager;
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
    public void insertFonction(Fonction fonction) {
        em.persist(fonction); // Fait le INSERT
        em.flush();
    }

    @Override
    public Fonction findFonctionById(Integer idFonction) {
        Fonction f = em.find(Fonction.class, idFonction);
        return f;
    }

    @Override
    public List<Fonction> findAllFonction() {
        return null;
    }

}