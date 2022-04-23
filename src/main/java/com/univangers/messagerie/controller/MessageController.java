/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.controller;

import com.univangers.messagerie.dto.AdresseDto;
import com.univangers.messagerie.dto.FichierDto;
import com.univangers.messagerie.dto.FonctionDto;
import com.univangers.messagerie.dto.MessageDto;
import com.univangers.messagerie.dto.PersonneDto;
import com.univangers.messagerie.fileReader.AttachFile;
import com.univangers.messagerie.fileReader.InfoPersonne;
import com.univangers.messagerie.fileReader.MailObject;
import com.univangers.messagerie.fileReader.MimeMessageReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.mail.MessagingException;
import org.springframework.web.bind.annotation.PathVariable;
import com.univangers.messagerie.services.MessageServiceInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author etud
 */
@RestController
@RequestMapping("/messagerie/message")
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

    @PostMapping("/test-insert/{nomFichier}")
    public Map testInsert(@PathVariable String nomFichier) throws MessagingException, IOException {

        MailObject mailObject = MimeMessageReader.readMessageFile("/home/etud/NetBeansProjects/messagerie/president_2010-06/" + nomFichier);

        MessageDto mDto = new MessageDto();

        mDto.setObject(mailObject.getSubject());
        mDto.setDate(mailObject.getSentDate());
        mDto.setBody(mailObject.getContent());

        // Expediteur
        AdresseDto expediteurDto = new AdresseDto();
        expediteurDto.setId(mailObject.getFrom().getMail());

        if (mailObject.getFrom().getLastName() != null || mailObject.getFrom().getFirstName() != null) {
            PersonneDto personneDto = new PersonneDto();
            personneDto.setId(mailObject.getFrom().getMail());
            personneDto.setNom(mailObject.getFrom().getLastName());
            personneDto.setPrenom(mailObject.getFrom().getFirstName());
            
            if (mailObject.getFonction() != null) {
                FonctionDto fonctionDto = new FonctionDto();
                fonctionDto.setTitle(mailObject.getFonction());
                personneDto.setFonctionDto(fonctionDto);
            }
            expediteurDto.setPersonneDto(personneDto);
        }

        mDto.setExpediteurDto(expediteurDto);

        List<InfoPersonne> destinataires = mailObject.getTo();
        List<AdresseDto> destinatairesDto = new ArrayList<>();
        for (InfoPersonne info : destinataires) {
            AdresseDto adresseDto = new AdresseDto(info.getMail());
            if (info.getLastName() != null || info.getFirstName() != null) {
                PersonneDto personneDto = new PersonneDto();
                personneDto.setId(info.getMail());
                personneDto.setNom(info.getLastName());
                personneDto.setPrenom(info.getFirstName());
                adresseDto.setPersonneDto(personneDto);

            }
            destinatairesDto.add(adresseDto);

        }
        
        if(mailObject.getFileList()!=null){
            List<AttachFile> fileList=mailObject.getFileList();
             List<FichierDto> fichierDtoList = new ArrayList<>();
            for(AttachFile af:fileList){
                FichierDto fichierDto=new FichierDto();
                fichierDto.setFilename(af.getFilename());
                fichierDto.setFilepath(af.getFilepath());
                fichierDto.setFiletype(af.getFiletype());
                fichierDtoList.add(fichierDto);
            }
            mDto.setFichierDtoList(fichierDtoList);
        }

        mDto.setDestinataireDtoList(destinatairesDto);

        messageService.insertMessageDto(mDto);

        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    @GetMapping("/get-message")
    public MessageDto getMessage() {
        MessageDto mDto = new MessageDto();
        mDto = messageService.findMessageDtoById(1);
        return mDto;
    }

}
