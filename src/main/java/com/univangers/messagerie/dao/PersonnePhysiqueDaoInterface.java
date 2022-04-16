/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.PersonnePhysique;

/**
 *
 * @author etud
 */
public interface PersonnePhysiqueDaoInterface {
    
    public void insertPersonnePhysique(PersonnePhysique personne);
    public PersonnePhysique findPersonnePhysiqueById(String idpers);
}
