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
import com.univangers.messagerie.util.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        MailObject mailObject = MimeMessageReader.readMessageFile(messageFilesDir + "/president_2010-11" + File.separator + nomFichier);

        return mailObject;
    }

    @PostMapping("/test-insert/{nomFichier}")
    public Map testInsert(@PathVariable String nomFichier) throws MessagingException, IOException {

        MailObject mailObject = MimeMessageReader.readMessageFile(messageFilesDir + "/president_2010-12" + File.separator + nomFichier);

        Map<String, String> result = new HashMap<>();

        if (mailObject == null) {
            result.put("result", "fichier endommagé");
        }
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
                personneDto.getFonctionDtoList().add(fonctionDto);
            }
            expediteurDto.setPersonneDto(personneDto);
        } else {
            // LISTE => A faire !!!
            ListeDto listDto = new ListeDto();
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

            } else {
                ListeDto listDto = new ListeDto();
                listDto.setId(info.getMail());
                adresseDto.setListeDto(listDto);
            }

            destinatairesDto.add(adresseDto);
        }
        mDto.setDestinataireDtoList(destinatairesDto);

        List<InfoPersonne> listPers = mailObject.getCc();
        List<AdresseDto> adrDtoList = new ArrayList<>();
        for (InfoPersonne infP : listPers) {
            AdresseDto adrDto = new AdresseDto(infP.getMail());
            if (infP.getFirstName() != null || infP.getLastName() != null) {
                PersonneDto persDto = new PersonneDto();
                persDto.setId(infP.getMail());
                persDto.setNom(infP.getLastName());
                persDto.setPrenom(infP.getFirstName());
                adrDto.setPersonneDto(persDto);
            } else {
                ListeDto listeDto = new ListeDto();
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

        result.put("result", "success");
        return result;
    }

    @PostMapping("/insert-all")
    public List<String> testInsertAll() {
        List<String> insertAllList = messageService.insertAll(messageFilesDir);
        return insertAllList;

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

    @GetMapping("/getbyid/{id}")
    public MessageDto getMessage(@PathVariable("id") Integer id) {
        MessageDto messageDto = messageService.findMessageDtoById(id);
        return messageDto;

    }

    @GetMapping("/get-message-between-date")
    public Map getMessageBetween(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        Date start = Utils.stringToDate(startDate);
        Date end = Utils.stringToDate(endDate);
        Integer count = messageService.countMessagesDtoBetweenDates(start, end);
        Map<String, Integer> result = new HashMap<>();
        result.put("result", count);
        return result;
    }

    @RequestMapping("/liste-message")
    public String listemessage(Model model, @RequestParam("id") Integer id, @RequestParam(value = "keyword", required = false) String keyWord) {
        List<MessageDto> messageDtoList;
        if (keyWord != null) {
            if (Utils.isValideEmail(keyWord)) {
                messageDtoList = messageService.findMessageDtoBySender(keyWord);
            } else {
                messageDtoList = messageService.findMessageDtoBySubject(keyWord);
            }
        } else {
            messageDtoList = messageService.findAllMessageDto();
        }

        model.addAttribute("messages", messageDtoList);
        if (id != 0) {
            MessageDto messageDto = messageService.findMessageDtoById(id);
            model.addAttribute("selectedMessage", messageDto);
        }

        return "./webHtml/liste-message";
    }

    @GetMapping("/home")
    public String home(Model model) {

        final Date start_06 = Utils.stringToDate("2010-06-01 00:00:00");
        final Date end_06 = Utils.stringToDate("2010-06-30 23:59:59");
        final Date end_07 = Utils.stringToDate("2010-07-31 23:59:59");
        final Date end_08 = Utils.stringToDate("2010-08-31 23:59:59");
        final Date end_09 = Utils.stringToDate("2010-09-30 23:59:59");
        final Date end_10 = Utils.stringToDate("2010-10-31 23:59:59");
        final Date end_11 = Utils.stringToDate("2010-11-30 23:59:59");
        final Date end_12 = Utils.stringToDate("2010-12-31 23:59:59");
        
        
        DataCounter counter = new DataCounter();
        Integer countMessages = messageService.countMessageDto();
        Integer countAdresses = adresseService.countAdresseDto();
        Integer countPersonnes = personneService.countPersonneDto();
        Integer countlistes = listeService.countListe();
        counter.setNombreMessages(countMessages);
        counter.setNombreAdresses(countAdresses);
        counter.setNombrePersonnes(countPersonnes);
        counter.setNombreListes(countlistes);
        
        
        Integer count_06 = messageService.countMessagesDtoBetweenDates(start_06, end_06);
        Integer count_07 = messageService.countMessagesDtoBetweenDates(end_06, end_07);
        Integer count_08 = messageService.countMessagesDtoBetweenDates(end_07, end_08);
        Integer count_09 = messageService.countMessagesDtoBetweenDates(end_08, end_09);
        Integer count_10 = messageService.countMessagesDtoBetweenDates(end_09, end_10);
        Integer count_11 = messageService.countMessagesDtoBetweenDates(end_10, end_11);
        Integer count_12 = messageService.countMessagesDtoBetweenDates(end_11, end_12);
        
        Map<String, Integer> messagePerMonth = new HashMap<>();
        messagePerMonth.put("count_06", count_06);
        messagePerMonth.put("count_07", count_07);
        messagePerMonth.put("count_08", count_08);
        messagePerMonth.put("count_09", count_09);
        messagePerMonth.put("count_10", count_10);
        messagePerMonth.put("count_11", count_11);
        messagePerMonth.put("count_12", count_12);
        
        model.addAttribute("messagePerMonth", messagePerMonth);
        model.addAttribute("counter", counter);

        return "./webHtml/home";
    }
    
    @GetMapping("/stats")
    public Map homeApi(Model model) {

        final Date start_06 = Utils.stringToDate("2010-06-01 00:00:00");
        final Date end_06 = Utils.stringToDate("2010-06-30 23:59:59");
        final Date end_07 = Utils.stringToDate("2010-07-31 23:59:59");
        final Date end_08 = Utils.stringToDate("2010-08-31 23:59:59");
        final Date end_09 = Utils.stringToDate("2010-09-30 23:59:59");
        final Date end_10 = Utils.stringToDate("2010-10-31 23:59:59");
        final Date end_11 = Utils.stringToDate("2010-11-30 23:59:59");
        final Date end_12 = Utils.stringToDate("2010-12-31 23:59:59");
        
        
        DataCounter counter = new DataCounter();
        Integer countMessages = messageService.countMessageDto();
        Integer countAdresses = adresseService.countAdresseDto();
        Integer countPersonnes = personneService.countPersonneDto();
        Integer countlistes = listeService.countListe();
        counter.setNombreMessages(countMessages);
        counter.setNombreAdresses(countAdresses);
        counter.setNombrePersonnes(countPersonnes);
        counter.setNombreListes(countlistes);
        
        
        Integer count_06 = messageService.countMessagesDtoBetweenDates(start_06, end_06);
        Integer count_07 = messageService.countMessagesDtoBetweenDates(end_06, end_07);
        Integer count_08 = messageService.countMessagesDtoBetweenDates(end_07, end_08);
        Integer count_09 = messageService.countMessagesDtoBetweenDates(end_08, end_09);
        Integer count_10 = messageService.countMessagesDtoBetweenDates(end_09, end_10);
        Integer count_11 = messageService.countMessagesDtoBetweenDates(end_10, end_11);
        Integer count_12 = messageService.countMessagesDtoBetweenDates(end_11, end_12);
        
        Map<String, Integer> messagePerMonth = new HashMap<>();
        messagePerMonth.put("count_06", count_06);
        messagePerMonth.put("count_07", count_07);
        messagePerMonth.put("count_08", count_08);
        messagePerMonth.put("count_09", count_09);
        messagePerMonth.put("count_10", count_10);
        messagePerMonth.put("count_11", count_11);
        messagePerMonth.put("count_12", count_12);
        
        model.addAttribute("messagePerMonth", messagePerMonth);
        model.addAttribute("counter", counter);
        Map<String, Object> result = new HashMap<>();
        result.put("counter", counter);
        result.put("messagePerMonth", messagePerMonth);
        
        return result;
    }

}
