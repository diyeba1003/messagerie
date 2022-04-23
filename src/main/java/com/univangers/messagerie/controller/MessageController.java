/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.controller;

import com.univangers.messagerie.dto.AdresseDto;
import com.univangers.messagerie.dto.MessageDto;
import com.univangers.messagerie.fileReader.MailObject;
import com.univangers.messagerie.fileReader.MimeMessageReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.mail.MessagingException;
import org.springframework.web.bind.annotation.PathVariable;
import com.univangers.messagerie.services.MessageServiceInterface;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author etud
 */
@Controller
@RequestMapping("/messagerie/messages")
@Transactional
public class MessageController {

    @Autowired
    private MessageServiceInterface messageService;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world !!!";
    }

    @GetMapping("/test-file-read/{nomFichier}")
    public MailObject afficheMessage(@PathVariable String nomFichier) throws FileNotFoundException, MessagingException, IOException {

        MailObject mailObject = MimeMessageReader.readMessageFile("/home/etud/NetBeansProjects/messagerie/president_2010-06/" + nomFichier);
       
        return mailObject;
    }

    @PostMapping("/test-insert")
    public void testInsert() {

        AdresseDto expediteurDto = new AdresseDto();
        expediteurDto.setId("matylayesouare@gmail.com");

        AdresseDto destinataireDto = new AdresseDto();
        destinataireDto.setId("hamidou@gmail.com");

        MessageDto mDto = new MessageDto();
        mDto.setObject("test");
        mDto.setDate(new Date());
        mDto.setBody("Test insertion Data");
        mDto.setExpediteurDto(expediteurDto);

        mDto.getDestinataireDtoList().add(destinataireDto);

        messageService.insertMessageDto(mDto);
    }

    @GetMapping("/get-message")
    public MessageDto getMessage() {
        MessageDto mDto = new MessageDto();
        mDto = messageService.findMessageDtoById(1);
        return mDto;
    }
    
    @GetMapping("/listeMessage")
    public String listemessage(Model model){
           List<MessageDto> messageDtoList=messageService.findAllMessageDto();
       model.addAttribute("messages", messageDtoList);
     
        return "listeMessage";
    }          
    
    
    @GetMapping("/home")
    public String accueil(){
       
        return "home";
    }          
    
}
