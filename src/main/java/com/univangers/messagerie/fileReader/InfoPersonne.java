/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.fileReader;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author etud
 */
public class InfoPersonne {

    @Getter
    @Setter
    String mail;

    @Getter
    @Setter
    String lastName;

    @Getter
    @Setter
    String firstName;

    public InfoPersonne(String mail) {
        this.mail = mail;
    }

    public InfoPersonne() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.mail);
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
        final InfoPersonne other = (InfoPersonne) obj;
        return this.mail.equalsIgnoreCase(other.mail);
    }
    
    
    

}
