/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dto.PersonneDto;


/**
 *
 * @author etud
 */
public interface PersonneServiceInterface {
    public void insertPersonnePhysiqueDto(PersonneDto  personneDto);
    public PersonneDto findPersonnePhysiqueDtoById(String id);
}
