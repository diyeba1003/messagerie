/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dao.AdresseDaoInterface;
import com.univangers.messagerie.dto.AdresseDto;
import com.univangers.messagerie.dto.FonctionDto;
import com.univangers.messagerie.dto.ListeDto;
import com.univangers.messagerie.dto.PersonneDto;
import com.univangers.messagerie.model.Adresse;
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
public class AdresseService implements AdresseServiceInterface {

    @Autowired
    private AdresseDaoInterface adresseDao;

    @Override
    public void insertAdresseDto(AdresseDto adresseDto) {
        Adresse adresse = convertToEntity(adresseDto);
        adresseDao.insertAdresse(adresse);
    }

    @Override
    public AdresseDto findAdresseDtoById(String id) {
        Adresse adresse = adresseDao.findAdresseById(id);
        return convertToDto(adresse);
    }

    @Override
    public Integer countAdresseDto() {
        Integer count = adresseDao.countAdresse();
        return count;
    }

    @Override
    public List<AdresseDto> findAllAdresseDto() {
        List<Adresse> adresseList = adresseDao.findAllAdresse();
        List<AdresseDto> adresseDtoList = new ArrayList<>();
        for (Adresse adresse : adresseList) {
            adresseDtoList.add(convertToDto(adresse));
        }
        return adresseDtoList;
    }
    
    @Override
    public void changeListeDtoToPersonneDto(AdresseDto adresseDto) {
        Adresse adresse=convertToEntity(adresseDto);
        adresseDao.updateListe(adresse);
    }

    @Override
    public void changePersonneDtoToListeDto(AdresseDto adresseDto) {
        Adresse adresse= convertToEntity(adresseDto);
        adresseDao.updatePersonne(adresse);
    }
    
    private Adresse convertToEntity(AdresseDto adresseDto) {
        Adresse adresse = new Adresse();
        adresse.setIdADRESSE(adresseDto.getId());
        return adresse;
    }

    private AdresseDto convertToDto(Adresse adresse) {
        AdresseDto adresseDto = new AdresseDto();
        adresseDto.setId(adresse.getIdADRESSE());
        if (adresse.getPersonne() != null) {
            Personne personne = adresse.getPersonne();
            PersonneDto personneDto = new PersonneDto();
            personneDto.setId(personne.getIdPERSONNE());
            personneDto.setNom(personne.getNom());
            personneDto.setPrenom(personne.getPrenom());
            if (personne.getPersonneFonctionList() != null) {
                List<FonctionDto> fonctionDtoList = new ArrayList<>();
                for (PersonneFonction pf : personne.getPersonneFonctionList()) {
                    FonctionDto fonctionDto = new FonctionDto();
                    fonctionDto.setId(pf.getFonction().getIdFONCTION());
                    fonctionDto.setTitle(pf.getFonction().getTitle());
                    fonctionDtoList.add(fonctionDto);
                }
                personneDto.setFonctionDtoList(fonctionDtoList);
            }
            adresseDto.setPersonneDto(personneDto);

        }
        if (adresse.getListe() != null) {
            ListeDto listeDto = new ListeDto();
            listeDto.setId(adresse.getListe().getIdLISTE());
            listeDto.setLibelle(adresse.getListe().getLibelle());
            adresseDto.setListeDto(listeDto);
        }

        return adresseDto;
    }
}
