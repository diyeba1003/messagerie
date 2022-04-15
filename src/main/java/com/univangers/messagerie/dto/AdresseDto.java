/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author etud
 */
public class AdresseDto {
    
    @Getter @Setter
    private String id;

    public AdresseDto() {
    }
    
    

    public AdresseDto(String id) {
        this.id = id;
    }
    
    
    
}
