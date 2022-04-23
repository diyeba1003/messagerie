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
public class FonctionDto {

    @Getter
    @Setter
    private Integer idFonction;

    @Getter
    @Setter
    private String title;

    public FonctionDto() {
    }

    public FonctionDto(Integer idFonction, String title) {
        this.idFonction = idFonction;
        this.title = title;
    }
}
