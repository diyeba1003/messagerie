/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Personne;

/**
 *
 * @author etud
 */
public interface PersonneDaoInterface {
    
    public void insertPersonne(Personne personne);
    public Personne findPersonneById(String idpers);
}
