/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dto.AdresseDto;

/**
 *
 * @author etud
 */
public interface AdresseServiceInterface {
    
    public void insertAdresseDto(AdresseDto adresseDto);
    public AdresseDto findAdresseById(String id);
    
}
