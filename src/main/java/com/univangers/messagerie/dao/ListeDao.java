/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Liste;
import javax.persistence.EntityManager;

/**
 *
 * @author etud
 */
public class ListeDao implements ListeDaoInterface {
    private transient EntityManager em;
    
    @Override
    public Liste findListeById(String idlist) {
      Liste liste = em.find(Liste.class, idlist);
        return liste;
    }


}
