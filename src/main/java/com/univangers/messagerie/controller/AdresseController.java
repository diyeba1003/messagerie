/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.controller;

import com.univangers.messagerie.dto.AdresseDto;
import com.univangers.messagerie.services.AdresseServiceInterface;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author etud
 */
@Controller
@Transactional
@RequestMapping("/messagerie/adresses")
public class AdresseController {

    @Autowired
    private AdresseServiceInterface adresseService;

    @GetMapping("/detail-user")
    public String info(Model model, @RequestParam("id") String id) {
        AdresseDto adresseDto = adresseService.findAdresseDtoById(id);
        model.addAttribute("adresseDto", adresseDto);
        return "/webHtml/detail-user";
    }
    
    @GetMapping("/user-infos")
    public String findAllPersonne(Model model) {
        List<AdresseDto> adresseDtoList = adresseService.findAllAdresseDto();
        model.addAttribute("adresseDtoList", adresseDtoList);
        return "/webHtml/user-infos";
    }

    
    @GetMapping("/all")
    public List<AdresseDto> findAll() {
        List<AdresseDto> adresseDtoList = adresseService.findAllAdresseDto();
        
        return adresseDtoList;
    }

}
