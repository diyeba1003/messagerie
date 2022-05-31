/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.controller;

import com.univangers.messagerie.dto.AdresseDto;
import com.univangers.messagerie.dto.ContactDto;
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
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/test-file-read/{nomFichier}")
    public MailObject afficheMessage(@PathVariable String nomFichier) throws FileNotFoundException, MessagingException, IOException {

        MailObject mailObject = MimeMessageReader.readMessageFile(messageFilesDir + "/president_2010-10" + File.separator + nomFichier);

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
    public String listemessage(Model model,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "keyword", required = false) String keyWord,
            @RequestParam(value = "filterType", required = false) String filterType) {

        List<MessageDto> messageDtoList = new ArrayList<>();

        if (keyWord != null && filterType != null) {
            if ("sender".equals(filterType)) {
                messageDtoList = messageService.findMessageDtoBySender(keyWord, true);
            } else if ("subject".equals(filterType)) {
                messageDtoList = messageService.findMessageDtoBySubject(keyWord);
            } else if ("destinataire".equals(filterType)) {
                messageDtoList=messageService.findMessageDtoByDestinataire(keyWord, Boolean.TRUE);
            }else if("destinataireCc".equals(filterType)){
                messageDtoList=messageService.findMessageDtoByDestinataireCc(keyWord, Boolean.TRUE);
            }
        } else {
            messageDtoList = messageService.findAllMessageDto();
        }

        model.addAttribute("messages", messageDtoList);
        if (id != null && id != 0) {
            MessageDto messageDto = messageService.findMessageDtoById(id);
            model.addAttribute("selectedMessage", messageDto);
            System.out.println("selectedMessage:\n" + messageDto.getBody());
        } else if (!messageDtoList.isEmpty()) {
            MessageDto messageDto = messageDtoList.get(0);
            model.addAttribute("selectedMessage", messageDto);
            System.out.println("selectedMessage:\n" + messageDto.getBody());
        }

        return "./webHtml/liste-message";
    }

    @GetMapping("/modal1")
    public String modal1() {
        return "./webHtml/detail-user";
    }

    @RequestMapping("/liste-message-par-periode")
    public String listemessageparperiode(Model model,
            @RequestParam(value = "selectedMonth", required = false) String selectedMonth,
            @RequestParam(value = "dayNumber", required = false) Integer dayNumber,
            @RequestParam(value = "index", required = false) Integer index,
            @RequestParam(value = "id", required = false) Integer id) {

        Date startDate = Utils.stringToDate("2010-06-01 00:00:00");
        Date endDate = Utils.stringToDate("2010-06-30 23:59:59");
        if (selectedMonth != null && dayNumber != null) {
            String startDateStr = selectedMonth + "-" + dayNumber + " 00:00:00";
            String endDateStr = selectedMonth + "-" + dayNumber + " 23:59:59";
            startDate = Utils.stringToDate(startDateStr);
            endDate = Utils.stringToDate(endDateStr);
            model.addAttribute("selectedMonth", selectedMonth);
            model.addAttribute("dayNumber", dayNumber);
        } else if (index != null) {
            switch (index) {
                case 0:
                    break;
                case 1:
                    startDate = Utils.stringToDate("2010-06-30 23:59:59");
                    endDate = Utils.stringToDate("2010-07-31 23:59:59");
                    break;
                case 2:
                    startDate = Utils.stringToDate("2010-07-31 23:59:59");
                    endDate = Utils.stringToDate("2010-08-31 23:59:59");
                    break;
                case 3:
                    startDate = Utils.stringToDate("2010-08-31 23:59:59");
                    endDate = Utils.stringToDate("2010-09-30 23:59:59");
                    break;
                case 4:
                    startDate = Utils.stringToDate("2010-09-30 23:59:59");
                    endDate = Utils.stringToDate("2010-10-31 23:59:59");
                    break;
                case 5:
                    startDate = Utils.stringToDate("2010-10-31 23:59:59");
                    endDate = Utils.stringToDate("2010-11-30 23:59:59");
                    break;
                case 6:
                    startDate = Utils.stringToDate("2010-11-30 23:59:59");
                    endDate = Utils.stringToDate("2010-12-31 23:59:59");
                    break;
            }
            model.addAttribute("index", index);
        }
        List<MessageDto> messageDtoList = messageService.findMessagesDtoBetweenDates(startDate, endDate);
        model.addAttribute("messages", messageDtoList);
        model.addAttribute("PerMonth", true);
        
        if (id != null) {
            MessageDto messageDto = messageService.findMessageDtoById(id);
            model.addAttribute("selectedMessage", messageDto);
        } else {
            MessageDto messageDto = messageDtoList.get(0);
            model.addAttribute("selectedMessage", messageDto);
        }

        return "./webHtml/liste-message-par-periode";

    }

    @GetMapping("/home")
    public String home(Model model,
            @RequestParam(value = "periode", required = false) String periode,
            @RequestParam(value = "selectedPeriode", required = false) String selectedPeriode) {

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
        model.addAttribute("counter", counter);

        if (periode != null && selectedPeriode != null) {
            Date startDate = Utils.stringToDate("2010-06-01 00:00:00");
            Date endDate = Utils.stringToDate("2010-06-30 23:59:59");

            switch (selectedPeriode) {
                case "2010-06":
                    break;
                case "2010-07":
                    startDate = Utils.stringToDate("2010-06-30 23:59:59");
                    endDate = Utils.stringToDate("2010-07-31 23:59:59");
                    break;
                case "2010-08":
                    startDate = Utils.stringToDate("2010-07-31 23:59:59");
                    endDate = Utils.stringToDate("2010-08-31 23:59:59");
                    break;
                case "2010-09":
                    startDate = Utils.stringToDate("2010-08-31 23:59:59");
                    endDate = Utils.stringToDate("2010-09-30 23:59:59");
                    break;
                case "2010-10":
                    startDate = Utils.stringToDate("2010-09-30 23:59:59");
                    endDate = Utils.stringToDate("2010-10-31 23:59:59");
                    break;
                case "2010-11":
                    startDate = Utils.stringToDate("2010-10-31 23:59:59");
                    endDate = Utils.stringToDate("2010-11-30 23:59:59");
                    break;
                case "2010-12":
                    startDate = Utils.stringToDate("2010-11-30 23:59:59");
                    endDate = Utils.stringToDate("2010-12-31 23:59:59");
                    break;

            }
            Map<String, Integer> statsPerMonth = messageService.getStatPerMonth(startDate, endDate);
            model.addAttribute("statsPerMonth", statsPerMonth);
            model.addAttribute("selectedMonth", selectedPeriode);
        } else {
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
        }

        return "./webHtml/home";
    }


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/messagerie/messages/home";
        } else {
            // normalize the file path
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                InputStream inputStream = file.getInputStream();
                MailObject mailObject = MimeMessageReader.readMessageInputStream(inputStream);
                if (mailObject == null) {
                    attributes.addFlashAttribute("alert", "Format de fichier incorrect " + fileName + '!');
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

            } catch (IOException | MessagingException ex) {
                if (ex instanceof FileSizeLimitExceededException) {
                    attributes.addFlashAttribute("alert", "File size exceeds limit!");
                    return "redirect:/messagerie/messages/home";
                }
                Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
            }

            attributes.addFlashAttribute("alert", "Message inséré avec succès !");

            return "redirect:/messagerie/messages/home";

        }
    }

    @GetMapping("/download")
    public void downloadPDFResource(
            HttpServletResponse response,
            @RequestParam("filePath") String filePath
    ) throws IOException {

        File file = new File(filePath);

        if (file.exists()) {
            //get the mimetype
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);

            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

            //Here we have mentioned it to show as attachment
            //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());

        }
    }

}
