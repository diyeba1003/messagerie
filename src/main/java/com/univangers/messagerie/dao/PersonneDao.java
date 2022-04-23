/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Personne;
import javax.persistence.EntityManager;

/**
 *
 * @author etud
 */
public class PersonneDao  implements PersonneDaoInterface{
    
     private transient EntityManager em;

    @Override
    public void insertPersonne(Personne personne) {
       em.persist(personne); // Fait le INSERT
        em.flush();
    }

    @Override
    public Personne findPersonneById(String idpers) {
       Personne personne = em.find(Personne.class, idpers);
        return personne;
    }

    
}
