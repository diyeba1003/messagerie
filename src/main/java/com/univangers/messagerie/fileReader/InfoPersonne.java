/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.fileReader;

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
    

}
