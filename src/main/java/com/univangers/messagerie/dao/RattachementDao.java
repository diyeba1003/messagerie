/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Rattachement;
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
public class RattachementDao implements RattachementDaoInterface {

    @PersistenceContext
    private transient EntityManager em;

    @Override
    public void insertRattachement(Rattachement rattachement) {
        em.persist(rattachement);
        em.flush();
    }

    @Override
    public Rattachement findRattachementById(Integer idRattachement) {
        Rattachement ra = em.find(Rattachement.class, idRattachement);
        return ra;
    }

    @Override
    public List<Rattachement> findAllRattachement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
