/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Personne;
import java.util.ArrayList;
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
public class PersonneDao implements PersonneDaoInterface {

    @PersistenceContext
    private transient EntityManager em;

    @Override
    public void insertPersonne(Personne personne) {
        em.persist(personne); // Fait le INSERT
        em.flush();
    }

    @Override
    public Personne findPersonneById(String idPersonne) {
        Personne personne = em.find(Personne.class, idPersonne);
        return personne;
    }

    @Override
    public Integer countPersonne() {
        Integer count = 0;
        try {
            Object object = em.createQuery("SELECT COUNT(p) FROM Personne p").getSingleResult();
            if (object != null) {
                count = (int) (long) object;
            }
        } catch (NoResultException e) {
            count = 0;
        }
        return count;
    }

    @Override
    public List<Personne> findAllPersonne() {
        List<Personne> personneList;
        try {
            personneList = em.createQuery("SELECT p FROM Personne p").getResultList();
        } catch (NoResultException e) {
            personneList = new ArrayList<>();

        }
        return personneList;

    }

    @Override
    public void updatePersonne(Personne personne) {
        em.merge(personne);
    }

    @Override
    public void deletePersonne(Personne personne) {
        if (personne != null) {
            em.remove(personne);
            System.out.println(">> Success remove Personne !!!");
            em.flush();
        }
    }
}
