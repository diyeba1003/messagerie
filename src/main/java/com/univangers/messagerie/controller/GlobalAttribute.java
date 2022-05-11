/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author etud
 */
@ControllerAdvice
public class GlobalAttribute {
       
    @ModelAttribute("searchField")
     public String getCurrentUser() {
        return "searchField";
     }
    
}
