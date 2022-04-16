/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dto.PersonnePhysiqueDto;

/**
 *
 * @author etud
 */
public interface PersonnePhysiqueServiceInterface {
    public void insertPersonnePhysiqueDto(PersonnePhysiqueDto personneDto);
    public PersonnePhysiqueDto findPersonnePhysiqueDtoById(String id);
}
