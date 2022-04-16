/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dao.PersonnePhysiqueDaoInterface;
import com.univangers.messagerie.dto.PersonnePhysiqueDto;
import com.univangers.messagerie.model.Adresse;
import com.univangers.messagerie.model.PersonnePhysique;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author etud
 */
public class PersonnePhysiqueService implements PersonnePhysiqueServiceInterface {

    @Autowired
    private PersonnePhysiqueDaoInterface personneDao;

    @Override
    public void insertPersonnePhysiqueDto(PersonnePhysiqueDto personneDto) {
        PersonnePhysique personne = convertToEntity(personneDto);
        personneDao.insertPersonnePhysique(personne);
    }

    @Override
    public PersonnePhysiqueDto findPersonnePhysiqueDtoById(String id) {
        PersonnePhysique personne = personneDao.findPersonnePhysiqueById(id);
        return convertToDto(personne);
    }

    private PersonnePhysique convertToEntity(PersonnePhysiqueDto personneDto) {
        PersonnePhysique personneP = new PersonnePhysique();
        
        /*
        Adresse adr= new Adresse();
        adr.setIdAdresse(personneDto.getIdAdresse());
        personneP.setAdresse(adr);
        */
         personneP.setNom(personneDto.getNom());
        personneP.setPrenom(personneDto.getPrenom());
      
        return personneP;
    }

    private PersonnePhysiqueDto convertToDto(PersonnePhysique personne) {
        PersonnePhysiqueDto personneDto= new PersonnePhysiqueDto();
        personneDto.setIdAdresse(personne.getIdAdresse());
        personneDto.setNom(personne.getNom());
        personneDto.setPrenom(personne.getPrenom());
        return personneDto;
    }

}
