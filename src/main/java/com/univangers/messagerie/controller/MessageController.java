/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.controller;

import com.univangers.messagerie.fileReader.MailObject;
import com.univangers.messagerie.fileReader.MimeMessageReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.mail.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author etud
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world !!!";
    }

    @GetMapping("/test-file-read/{nomFichier}")
    public MailObject afficheMessage(@PathVariable String nomFichier) throws FileNotFoundException, MessagingException, IOException {

        MailObject mailObject = MimeMessageReader.readMessageFile("/home/etud/NetBeansProjects/messagerie/president_2010-06/" + nomFichier);

        return mailObject;
    }
}
