/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dto.FichierDto;
import com.univangers.messagerie.dto.MessageDto;
import com.univangers.messagerie.model.Fichier;
import com.univangers.messagerie.model.Message;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author etud
 */
@Service
@Transactional
public class FichierService  implements FichierServiceInterface{

    @Override
    public void insertFichierDto(FichierDto fichierdto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FichierDto findFichierById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
       if(fichierDto.getMessageDto()!= null){
            Message message= new Message();
            message.setIdMESSAGE(fichierDto.getMessageDto().getId());
            fichier.setMessageID(message);
       
        }
       
        return fichier;
    }
    /**
     * Convertir un DTO en entity
     * 
     * @param fichier
     * @return 
     */
    private FichierDto convertToDto(Fichier fichier){
        
        FichierDto fichierDto = new FichierDto();
        fichierDto.setId(fichier.getIdFICHIER());
        fichierDto.setFiletype(fichier.getFiletype());
        fichierDto.setFilename(fichier.getFilename());
        fichierDto.setFilepath(fichier.getFilepath());
        if(fichier.getMessageID()!=null)
        {
            MessageDto messageDto=new MessageDto ();
            messageDto.setId(fichier.getMessageID().getIdMESSAGE());
            fichierDto.setMessageDto(messageDto);
        }
        
        return fichierDto;
    }
}
