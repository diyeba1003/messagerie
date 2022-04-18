/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dao.AdresseDaoInterface;
import com.univangers.messagerie.dto.AdresseDto;
import com.univangers.messagerie.dto.MessageDto;
import com.univangers.messagerie.model.Adresse;
import com.univangers.messagerie.model.Message;
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

    private Adresse convertToEntity(AdresseDto adresseDto) {
        Adresse adresse = new Adresse();
        adresse.setIdADRESSE(adresseDto.getId());
        return adresse;
    }

    @Override
    public void insertAdresseDto(AdresseDto adresseDto) {
        Adresse adresse = convertToEntity(adresseDto);
        adresseDao.insertAdresse(adresse);
    }

    @Override
    public AdresseDto findAdresseById(String id) {
        Adresse ad = adresseDao.findAdresseById(id);
        return convertToDto(ad);
    }

    private AdresseDto convertToDto(Adresse adresse) {

        AdresseDto adresseDto = new AdresseDto();
        adresseDto.setId(adresse.getIdADRESSE());
        return adresseDto;
    }

}
