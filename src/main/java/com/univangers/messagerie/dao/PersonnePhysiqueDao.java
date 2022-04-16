/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.PersonnePhysique;
import javax.persistence.EntityManager;

/**
 *
 * @author etud
 */
public class PersonnePhysiqueDao  implements PersonnePhysiqueDaoInterface{
    
     private transient EntityManager em;

    @Override
    public void insertPersonnePhysique(PersonnePhysique personne) {
       em.persist(personne); // Fait le INSERT
        em.flush();
    }

    @Override
    public PersonnePhysique findPersonnePhysiqueById(String idpers) {
       PersonnePhysique personne = em.find(PersonnePhysique.class, idpers);
        return personne;
    }

    
}
