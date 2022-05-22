/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.controller;

import com.univangers.messagerie.dto.AdresseDto;
import com.univangers.messagerie.services.AdresseServiceInterface;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     @GetMapping("/nbAdresse")
    public String findAll(Model model) {
        List<AdresseDto> adresseDtoList = adresseService.findAllAdresseDto();
        model.addAttribute("adresseDtoList", adresseDtoList);
        return "/webHtml/nombre-adresse";
    }
    @GetMapping("/liste-info")
    public String findAllListe(Model model) {
        List<AdresseDto> adresseDtoList = adresseService.findAllAdresseDto();
        model.addAttribute("adresseDtoList", adresseDtoList);
        return "/webHtml/liste-info";
    }
    
    @GetMapping("/all")
    public List<AdresseDto> findAll() {
        List<AdresseDto> adresseDtoList = adresseService.findAllAdresseDto();
        
        return adresseDtoList;
    }
    
    @RequestMapping("/persToListe/{adr}")
    public String changePersonneToListe(@PathVariable("adr") String adr){
        System.out.println("adresse: "+adr);
        adresseService.changePersonneDtoToListeDto(adr);
        return "redirect:/messagerie/adresses/user-infos";
    }
    
   @RequestMapping("/listeToPers/{adr}")
    public String changeListeToPersonne(@PathVariable("adr") String adr){
            System.out.println("adresse: "+adr);
        adresseService.changeListeDtoToPersonneDto(adr);
        return "redirect:/messagerie/adresses/user-infos";
    }
    
    @GetMapping("/contacts/{id}")
    public List<AdresseDto> findContactList(@PathVariable("id") String id) {
        List<AdresseDto> adresseDtoList = adresseService.getAdresseDtoContactList(id);
        
        return adresseDtoList;
    }
    
    @GetMapping("/adr-has-contact")
    public Map adrHasContactList(@RequestParam("idAdresse") String idAdresse, @RequestParam("idContact") String idContact) {
        Boolean adrHasContact = adresseService.adresseDtoHasContact(idAdresse, idContact);
        
        Map<String, Boolean> result = new HashMap<>();
        
        result.put("adrHasContact", adrHasContact);
        
        return result;
    }
}
