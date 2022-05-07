/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.controller;

import com.univangers.messagerie.dto.PersonneDto;
import com.univangers.messagerie.services.PersonneServiceInterface;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author etud
 */
@Controller
@RequestMapping("messagerie/personne")
@Transactional
public class PersonneController {

    @Autowired
    private PersonneServiceInterface personneService;

    /*   @GetMapping("/user-infos")
    public String findPersonneById(@RequestParam(value="id") String id ,Model model ){
        PersonneDto personneDto= personneService.findPersonneDtoById(id);
        model.addAttribute("personne", personneDto);
        return "/webHtml/user-infos";
    }
     */
    @GetMapping("/user-infos")
    public String findAllPersonne(Model model) {
        List<PersonneDto> listPers = personneService.findAllPersonneDto();
        model.addAttribute("listePersonne", listPers);
        return "/webHtml/user-infos";
    }

    @GetMapping("/modifier/{id}")
    public String update(@PathVariable(value = "id") String id, Model model) {
        PersonneDto personneDto = personneService.findPersonneDtoById(id);
        model.addAttribute("personneDto", personneDto);
        return "/webHtml/modifier";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") String id, PersonneDto personneDto) {
        personneDto.setId(id);
        System.out.println(">> "+personneDto);
        personneService.updatePersonneDto(personneDto);
        return "redirect:/messagerie/personne/user-infos";
    }
}
