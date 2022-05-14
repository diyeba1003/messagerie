/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dto.ListeDto;
import com.univangers.messagerie.model.Liste;

/**
 *
 * @author etud
 */
public interface ListeServiceInterface {
    public ListeDto findListeDtoById(String idlist);
    public Integer countListe();
    
    public void updateListDto(ListeDto listeDto);
}
