/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.controller;

import com.univangers.messagerie.services.ListeServiceInterface;
import javax.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author etud
 */
@Controller
@RequestMapping("messagerie/personne")
@Transactional
public class ListeController {
    private ListeServiceInterface listeService;
}
