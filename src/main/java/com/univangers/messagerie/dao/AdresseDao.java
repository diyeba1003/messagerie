/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Adresse;
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
public class AdresseDao implements AdresseDaoInterface {

    @PersistenceContext
    private transient EntityManager em;

    @Override
    public void insertAdresse(Adresse adresse) {
        em.persist(adresse); // Fait le INSERT
        em.flush();
    }

    @Override
    public Adresse findAdresseById(String idAdresse) {
        try {
            return em.find(Adresse.class, idAdresse);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Adresse> findAllAdresse() {
        List<Adresse> adresseList;
        try {
            adresseList = em.createQuery("SELECT a FROM Adresse a").getResultList();
        } catch (NoResultException e) {
            adresseList = new ArrayList<>();
        }
        return adresseList;

    }

    @Override
    public Integer countAdresse() {
        Integer count = 0;
        try {
            Object object = em.createQuery("SELECT COUNT(a) FROM Adresse a ").getSingleResult();
            if (object != null) {
                count = (int) (long) object;
            }
        } catch (NoResultException e) {
            count = 0;
        }
        return count;
    }

    @Override
    public void update(Adresse adresse) {
        em.merge(adresse);
    }

    @Override
    public Boolean adresseHasContact(String idADRESSE, String idContact) {
        Adresse adresse = em.find(Adresse.class, idADRESSE);
        if (adresse != null) {
            for (Adresse contact : adresse.getAdresseContactList()) {
                if (contact.getIdADRESSE().equalsIgnoreCase(idContact)) {
                    return true;
                }
            }
        }
        return false;
    }

}
