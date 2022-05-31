/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author etud
 */
public class ContactDto {

    @Getter
    @Setter
    private String idContact;

    @Getter
    @Setter
    private List<AdresseDto> contactsFrom;

    @Getter
    @Setter
    private List<AdresseDto> contactsTo;

    @Getter
    @Setter
    private List<AdresseDto> contactsCc;
    
    
}
