/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Personne;
import java.util.ArrayList;
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
public class PersonneDao implements PersonneDaoInterface {

    @PersistenceContext
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

    @Override
    public Integer countPersonne() {
        Integer count = 0;
        Object object = em.createQuery("SELECT COUNT(p) FROM Personne p").getSingleResult();
        if (object != null) {
            count = (int) (long) object;
        }
        return count;
    }

    @Override
    public List<Personne> findAllPersonne() {
        List<Personne> personneList = new ArrayList<>();

       personneList = em.createQuery("SELECT p FROM Personne p").getResultList();
       return personneList;

    }
    @Override
    public void updatePersonne(Personne personne) {
        em.merge(personne);
    }
    
    public void deleteMessage(String idPers) {
      Personne personneDelete = findPersonneById(idPers);
        if (personneDelete != null) {
            em.remove(personneDelete);
        }
    }
}
