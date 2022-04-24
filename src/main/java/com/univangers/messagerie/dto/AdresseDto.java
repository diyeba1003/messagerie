/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dto;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author etud
 */
public class AdresseDto {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private PersonneDto personneDto;

    public AdresseDto() {
    }

    public AdresseDto(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AdresseDto other = (AdresseDto) obj;
        return Objects.equals(this.id, other.id);
    }

}
