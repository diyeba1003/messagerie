/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dao.PersonneDaoInterface;
import com.univangers.messagerie.dto.FonctionDto;
import com.univangers.messagerie.dto.PersonneDto;
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
        Personne personne = new Personne();
        personne.setIdPERSONNE(personneDto.getId());
        personne.setNom(personneDto.getNom());
        personne.setPrenom(personneDto.getPrenom());

        return personne;
    }

    private PersonneDto convertToDto(Personne personne) {
        PersonneDto personneDto = new PersonneDto();
        personneDto.setId(personne.getIdPERSONNE());
        personneDto.setNom(personne.getNom());
        personneDto.setPrenom(personne.getPrenom());
        if(personne.getPersonneFonctionList()!=null){
            List<FonctionDto> fonctionDtoList= new ArrayList<>();
            for(PersonneFonction pf : personne.getPersonneFonctionList()){
                FonctionDto fonctDto= new FonctionDto();
                fonctDto.setId(pf.getFonction().getIdFONCTION());
                fonctDto.setTitle(pf.getFonction().getTitle());
                fonctionDtoList.add(fonctDto);
            }
            personneDto.setFonctionDtoList(fonctionDtoList);
        }
        return personneDto;
    }

    @Override
    public Integer countPersonneDto() {
        Integer count = personneDao.countPersonne();
        return count;
    }

    @Override
    public List<PersonneDto> findAllPersonneDto() {
        List<Personne> personneList = new ArrayList<>();
        List<PersonneDto> personneDtoList = new ArrayList<>();
        personneList = personneDao.findAllPersonne();
        if (!personneList.isEmpty()) {
            for (Personne p : personneList) {
                personneDtoList.add(convertToDto(p));
            }
        }
        return personneDtoList;
    }

    public void updatePersonneDto(PersonneDto personneDto) {
     Personne personne=convertToEntity(personneDto);
      personneDao.updatePersonne(personne);
    }

    @Override
    public void deletePersonneDto(PersonneDto personneDto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

}
