/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Liste;

/**
 *
 * @author etud
 */
public interface ListeDaoInterface {

    public Liste findListeById(String idlist);
    
    public Integer countListe();
    
    public void updateList(Liste liste);
    
    public void deleteListe(Liste liste);
    
}
