/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Fonction;
import java.util.List;

/**
 *
 * @author etud
 */
public interface FonctionDaoInterface {
    
    public void insertFonction(Fonction fonction);
    
    public Fonction findFonctionById(Integer idFONCTION);
    
    public Fonction findFonctionByTitle(String title);
    
    public List<Fonction> findAllFonction();
}
