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
public class PersonneDto {
    @Getter
    @Setter
    private String idAdresse;
    @Getter
    @Setter
    private String nom;
    @Getter
    @Setter
    private String prenom;

    public PersonneDto() {
    }

    public PersonneDto(String idAdresse, String nom, String prenom) {
        this.idAdresse = idAdresse;
        this.nom = nom;
        this.prenom = prenom;
    }
}
