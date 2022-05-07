/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dto.PersonneDto;
import java.util.List;

/**
 *
 * @author etud
 */
public interface PersonneServiceInterface {

    public void insertPersonneDto(PersonneDto personneDto);

    public PersonneDto findPersonneDtoById(String id);
    public  List<PersonneDto> findAllPersonneDto();

    public Integer countPersonneDto();
    public  void updatePersonneDto(PersonneDto personneDto);
    public void deletePersonneDto(PersonneDto personneDto);
}
