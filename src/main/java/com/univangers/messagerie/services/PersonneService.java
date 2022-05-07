/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dao.PersonneDaoInterface;
import com.univangers.messagerie.dto.FonctionDto;
import com.univangers.messagerie.dto.PersonneDto;
import com.univangers.messagerie.dto.PersonneFonctionDto;
import com.univangers.messagerie.model.Fonction;
import com.univangers.messagerie.model.Personne;
import com.univangers.messagerie.model.PersonneFonction;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author etud
 */
@Service
@Transactional
public class PersonneService implements PersonneServiceInterface {

    @Autowired
    private PersonneDaoInterface personneDao;

    @Override
    public void insertPersonneDto(PersonneDto personneDto) {
        Personne personne = convertToEntity(personneDto);
        personneDao.insertPersonne(personne);
    }

    @Override
    public PersonneDto findPersonneDtoById(String id) {
        Personne personne = personneDao.findPersonneById(id);
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
        PersonneDto personneDto = new PersonneDto();
        personneDto.setId(personne.getIdPERSONNE());
        personneDto.setNom(personne.getNom());
        personneDto.setPrenom(personne.getPrenom());
        return personneDto;
    }

    @Override
    public Integer countPersonneDto() {
        Integer count = personneDao.countPersonne();
        return count;
    }

}
