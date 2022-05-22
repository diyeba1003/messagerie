/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dto.AdresseDto;
import java.util.List;

/**
 *
 * @author etud
 */
public interface AdresseServiceInterface {

    public void insertAdresseDto(AdresseDto adresseDto);

    public AdresseDto findAdresseDtoById(String id);

    public Integer countAdresseDto();

    public List<AdresseDto> findAllAdresseDto();

    public void changeListeDtoToPersonneDto(String listeId);

    public void changePersonneDtoToListeDto(String personneId);

    public Boolean adresseDtoHasContact(String idAdresse, String idContact);
    
    public List<AdresseDto> getAdresseDtoContactList(String idAdresse);
}
