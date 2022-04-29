/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.controller;

import com.univangers.messagerie.dto.AdresseDto;
import com.univangers.messagerie.dto.DataCounter;
import com.univangers.messagerie.dto.FichierDto;
import com.univangers.messagerie.dto.FonctionDto;
import com.univangers.messagerie.dto.ListeDto;
import com.univangers.messagerie.dto.MessageDto;
import com.univangers.messagerie.dto.PersonneDto;
import com.univangers.messagerie.dto.PersonneFonctionDto;
import com.univangers.messagerie.fileReader.AttachFile;
import com.univangers.messagerie.fileReader.InfoPersonne;
import com.univangers.messagerie.fileReader.MailObject;
import com.univangers.messagerie.fileReader.MimeMessageReader;
import com.univangers.messagerie.services.AdresseServiceInterface;
import com.univangers.messagerie.services.ListeServiceInterface;
import java.io.IOException;
import javax.mail.MessagingException;
import org.springframework.web.bind.annotation.PathVariable;
import com.univangers.messagerie.services.MessageServiceInterface;
import com.univangers.messagerie.services.PersonneServiceInterface;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author etud
 */
@RestController
@RequestMapping("/messagerie/messages")
@Transactional
public class MessageController {

    @Autowired
    private MessageServiceInterface messageService;

    @Autowired
    private AdresseServiceInterface adresseService;

    @Autowired
    private PersonneServiceInterface personneService;

    @Autowired
    private ListeServiceInterface listeService;

    @Value("${message.files.directory}")
    private String messageFilesDir;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world !!!";
    }

    @GetMapping("/test-file-read/{nomFichier}")
    public MailObject afficheMessage(@PathVariable String nomFichier) throws FileNotFoundException, MessagingException, IOException {

        MailObject mailObject = MimeMessageReader.readMessageFile(messageFilesDir + File.separator + nomFichier);

        return mailObject;
    }

    @PostMapping("/test-insert/{nomFichier}")
    public Map testInsert(@PathVariable String nomFichier) throws MessagingException, IOException {

        MailObject mailObject = MimeMessageReader.readMessageFile(messageFilesDir + File.separator + nomFichier);

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
                PersonneFonctionDto personneFonctionDto = new PersonneFonctionDto();
                personneFonctionDto.setFonctionDto(fonctionDto);
                personneFonctionDto.setPersonneDto(personneDto);
                personneDto.getPersonneFonctionDtoList().add(personneFonctionDto);
            } 
            expediteurDto.setPersonneDto(personneDto);
        }
        else {
                // LISTE => A faire !!!
               ListeDto listDto =new ListeDto();
               listDto.setId(mailObject.getFrom().getMail());
               expediteurDto.setListeDto(listDto);
               
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

            }else {
                //LISTE => A faire !!!
                
                   ListeDto listDto = new ListeDto();
                   listDto.setId(info.getMail());
                   adresseDto.setListeDto(listDto);
                    
                 }
            
            destinatairesDto.add(adresseDto);
        }     
        mDto.setDestinataireDtoList(destinatairesDto);
        
       List<InfoPersonne> listPers= mailObject.getCc();
       List<AdresseDto> adrDtoList= new ArrayList<>();
       for(InfoPersonne infP : listPers){
           AdresseDto adrDto= new AdresseDto(infP.getMail());
           if(infP.getFirstName() != null || infP.getLastName() !=null){
               PersonneDto persDto= new PersonneDto();
               persDto.setId(infP.getMail());
               persDto.setNom(infP.getLastName());
               persDto.setPrenom(infP.getFirstName());
               adrDto.setPersonneDto(persDto);
           }
           //fait par moi 
           else{
               ListeDto listeDto= new ListeDto();
               listeDto.setId(infP.getMail());
               adrDto.setListeDto(listeDto);
           }
            adrDtoList.add(adrDto);
       }       
        mDto.setDestinataireCopieDtoList(adrDtoList);
       
        if (mailObject.getFileList() != null) {
            List<AttachFile> fileList = mailObject.getFileList();
            List<FichierDto> fichierDtoList = new ArrayList<>();
            for (AttachFile af : fileList) {
                FichierDto fichierDto = new FichierDto();
                fichierDto.setFilename(af.getFilename());
                fichierDto.setFilepath(af.getFilepath());
                fichierDto.setFiletype(af.getFiletype());
                fichierDtoList.add(fichierDto);
            }
            mDto.setFichierDtoList(fichierDtoList);
        }

        messageService.insertMessageDto(mDto);

        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    @GetMapping("/get-message")
    public DataCounter getMessage() {
        DataCounter counter = new DataCounter();
        Integer countMessages = messageService.countMessageDto();
        Integer countAdresses = adresseService.countAdresseDto();
        Integer countPersonnes = personneService.countPersonneDto();
        Integer countlistes = listeService.countListe();
        counter.setNombreMessages(countMessages);
        counter.setNombreAdresses(countAdresses);
        counter.setNombrePersonnes(countPersonnes);
        counter.setNombreListes(countlistes);
        return counter;

    }

    @GetMapping("/listeMessage")
    public String listemessage(Model model, @RequestParam("id") Integer id) {
        List<MessageDto> messageDtoList = messageService.findAllMessageDto();
        model.addAttribute("messages", messageDtoList);
        if (id != 0) {
            MessageDto messageDto = messageService.findMessageDtoById(id);
            model.addAttribute("selectedMessage", messageDto);
        }
        return "./webHtml/listeMessage";
    }

    @GetMapping("/listeMessageId")
    public String listemessageid(Model model, @Param("id") Integer id) {

        //model.addAttribute("test", "Mon test");
        return "listeMessageId";
    }

    @GetMapping("/home")
    public String accueil(Model model) {

        DataCounter counter = new DataCounter();
        Integer countMessages = messageService.countMessageDto();
        Integer countAdresses = adresseService.countAdresseDto();
        Integer countPersonnes = personneService.countPersonneDto();
        Integer countlistes = listeService.countListe();
        counter.setNombreMessages(countMessages);
        counter.setNombreAdresses(countAdresses);
        counter.setNombrePersonnes(countPersonnes);
        counter.setNombreListes(countlistes);

        model.addAttribute("counter", counter);

        return "./webHtml/home";
    }

}
