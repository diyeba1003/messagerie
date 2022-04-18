/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dto.FichierDto;

/**
 *
 * @author etud
 */
public interface FichierServiceInterface {
    
    public void insertFichierDto(FichierDto fichierdto);
   public FichierDto findFichierById(Integer id);
}
