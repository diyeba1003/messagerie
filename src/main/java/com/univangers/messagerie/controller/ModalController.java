/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author etud
 */
@Controller
@RequestMapping("modals")
public class ModalController {

    @GetMapping("modal1")
    public String modal1() {
        return "modal1";
    }

    @GetMapping("modal2")
    public String modal2(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "webHtml/upload-modal";
    }
}
