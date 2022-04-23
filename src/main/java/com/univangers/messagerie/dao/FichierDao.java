/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Fichier;
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
public class FichierDao implements FichierDaoInterface {

    @PersistenceContext
    private transient EntityManager em;

    @Override
    public void insertFichier(Fichier fichier) {
        em.persist(fichier); // Fait le INSERT
        em.flush();
    }

    @Override
    public Fichier findFichierById(Integer idFichier) {
        Fichier f = em.find(Fichier.class, idFichier);
        return f;
    }

    public List<Fichier> findAllFichier() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
