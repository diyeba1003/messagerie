/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dto.FonctionDto;

/**
 *
 * @author etud
 */
public interface FonctionServiceInterface {

    public void insertFonctionDto(FonctionDto fonctionDto);

    public FonctionDto findFonctionById(Integer id);
}
