/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dao.FichierDaoInterface;
import com.univangers.messagerie.dto.FichierDto;
import com.univangers.messagerie.model.Fichier;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author etud
 */
@Service
@Transactional
public class FichierService implements FichierServiceInterface {

    @Autowired
    private FichierDaoInterface fichierDao;

    @Override
    public void insertFichierDto(FichierDto fichierDto) {
        Fichier fichier = convertToEntity(fichierDto);
        fichierDao.insertFichier(fichier);
    }

    @Override
    public FichierDto findFichierById(Integer id) {
        Fichier fich = fichierDao.findFichierById(id);
        return convertToDto(fich);
    }

    /**
     * Convertir un DTO en entity
     *
     * @param fichierDto
     * @return
     */
    private Fichier convertToEntity(FichierDto fichierDto) {
        Fichier fichier = new Fichier();
        fichier.setIdFICHIER(fichierDto.getId());
        fichier.setFiletype(fichierDto.getFiletype());
        fichier.setFilename(fichierDto.getFilename());
        fichier.setFilepath(fichierDto.getFilepath());
   
        return fichier;
    }

    /**
     * Convertir un DTO en entity
     *
     * @param fichier
     * @return
     */
    private FichierDto convertToDto(Fichier fichier) {

        FichierDto fichierDto = new FichierDto();
        fichierDto.setId(fichier.getIdFICHIER());
        fichierDto.setFiletype(fichier.getFiletype());
        fichierDto.setFilename(fichier.getFilename());
        fichierDto.setFilepath(fichier.getFilepath());
       
        return fichierDto;
    }
}
