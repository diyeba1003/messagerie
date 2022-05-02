/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dao.FonctionDaoInterface;
import com.univangers.messagerie.dto.FonctionDto;
import com.univangers.messagerie.model.Fonction;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author etud
 */
@Service
@Transactional
public class FonctionService implements FonctionServiceInterface {

    @Autowired
    private FonctionDaoInterface fonctionDao;

    private Fonction convertToEntity(FonctionDto fonctionDto) {
        Fonction fonction = new Fonction();
        fonction.setIdFONCTION(fonctionDto.getId());
        fonction.setTitle(fonctionDto.getTitle());
        return fonction;
    }

    @Override
    public void insertFonctionDto(FonctionDto fonctionDto) {
        Fonction fonction = convertToEntity(fonctionDto);
        fonctionDao.insertFonction(fonction);
    }

    @Override
    public FonctionDto findFonctionById(Integer id) {
        Fonction f = fonctionDao.findFonctionById(id);
        return convertToDto(f);
    }

    private FonctionDto convertToDto(Fonction fonction) {

        FonctionDto fonctionDto = new FonctionDto();
        fonctionDto.setId(fonction.getIdFONCTION());
        fonctionDto.setTitle(fonction.getTitle());
        return fonctionDto;
    }

}
