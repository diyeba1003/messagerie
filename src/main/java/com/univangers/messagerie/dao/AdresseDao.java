/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Adresse;
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
public class AdresseDao implements AdresseDaoInterface {

    @PersistenceContext
    private transient EntityManager em;

    
    @Override
    public void insertAdresse(Adresse adresse) {
        em.persist(adresse); // Fait le INSERT
        em.flush();; }
    public Adresse findAdresseById(String idAdresse) {
         Adresse a = em.find(Adresse.class, idAdresse);
        return a;
    }

    @Override
    public List<Adresse> findAllAdresse() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }    
}
