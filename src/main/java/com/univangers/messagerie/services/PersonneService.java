/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dao.PersonneDaoInterface;
import com.univangers.messagerie.dto.PersonneDto;
import com.univangers.messagerie.model.Adresse;
import com.univangers.messagerie.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author etud
 */
public class PersonneService implements PersonneServiceInterface {

    @Autowired
    private PersonneDaoInterface personneDao;

    @Override
    public void insertPersonnePhysiqueDto(PersonneDto personneDto) {
        Personne personne = convertToEntity(personneDto);
        personneDao.insertPersonnePhysique(personne);
    }

    @Override
    public PersonneDto findPersonnePhysiqueDtoById(String id) {
        Personne personne = personneDao.findPersonnePhysiqueById(id);
        return convertToDto(personne);
    }

    private Personne convertToEntity(PersonneDto personneDto) {
        Personne personneP = new Personne();
        
        /*
        Adresse adr= new Adresse();
        adr.setIdAdresse(personneDto.getIdAdresse());
        personneP.setAdresse(adr);
        */
         personneP.setNom(personneDto.getNom());
        personneP.setPrenom(personneDto.getPrenom());
      
        return personneP;
    }

    private PersonneDto convertToDto(Personne personne) {
        PersonneDto personneDto= new PersonneDto();
        personneDto.setIdAdresse(personne.getIdPERSONNE());
        personneDto.setNom(personne.getNom());
        personneDto.setPrenom(personne.getPrenom());
        return personneDto;
    }

}
