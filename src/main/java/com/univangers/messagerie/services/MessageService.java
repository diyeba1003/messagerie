/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dao.AdresseDaoInterface;
import com.univangers.messagerie.dao.FonctionDaoInterface;
import com.univangers.messagerie.dao.MessageDaoInterface;
import com.univangers.messagerie.dto.AdresseDto;
import com.univangers.messagerie.dto.FichierDto;
import com.univangers.messagerie.dto.FonctionDto;
import com.univangers.messagerie.dto.ListeDto;
import com.univangers.messagerie.dto.MessageDto;
import com.univangers.messagerie.dto.PersonneDto;
import com.univangers.messagerie.dto.PersonneFonctionDto;
import com.univangers.messagerie.fileReader.AttachFile;
import com.univangers.messagerie.fileReader.FileScan;
import com.univangers.messagerie.fileReader.InfoPersonne;
import com.univangers.messagerie.fileReader.MailObject;
import com.univangers.messagerie.fileReader.MimeMessageReader;
import com.univangers.messagerie.model.Adresse;
import com.univangers.messagerie.model.Fichier;
import com.univangers.messagerie.model.Fonction;
import com.univangers.messagerie.model.Liste;
import com.univangers.messagerie.model.Message;
import com.univangers.messagerie.model.Personne;
import com.univangers.messagerie.model.PersonneFonction;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author etud
 */
@Service
@Transactional
public class MessageService implements MessageServiceInterface {

    @Autowired
    private MessageDaoInterface messageDao;

    @Autowired
    private AdresseDaoInterface adresseDao;

    @Autowired
    private FonctionDaoInterface fonctionDao;

    @Override
    public void insertMessageDto(MessageDto messageDto) {
        Message message = convertToEntity(messageDto);
        messageDao.insertMessage(message);
    }

    @Override
    public List<String> insertAll(String rep) {
        List<String> insertedFileList = new ArrayList<>();
        try {
            insertedFileList = this.insertAllFiles(rep);
        } catch (MessagingException | IOException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return insertedFileList;
    }

    @Override
    public MessageDto findMessageDtoById(Integer id) {
        Message message = messageDao.findMessageById(id);
        return convertToDto(message);
    }

    @Override
    public Integer countMessageDto() {

        Integer count = messageDao.countMessage();
        return count;
    }

    @Override
    public List<MessageDto> findAllMessageDto() {
        List<Message> messageList = new ArrayList<>();
        List<MessageDto> messageDtoList = new ArrayList<>();
        messageList = messageDao.findAllMessage();
        if (!messageList.isEmpty()) {
            for (Message m : messageList) {
                messageDtoList.add(convertToDto(m));
            }
        }
        return messageDtoList;
    }

    @Override
    public void updateMessageDto(MessageDto messageDto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteMessageDto(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Convertir un DTO en entity
     *
     * @param messageDto
     * @return
     */
    private Message convertToEntity(MessageDto messageDto) {
        Message message = new Message();
        message.setIdMESSAGE(messageDto.getId());
        message.setSentdate(messageDto.getDate());
        message.setSubject(messageDto.getObject());
        message.setBody(messageDto.getBody());
        Adresse adrSender = null;
        if (messageDto.getExpediteurDto() != null) {
            AdresseDto expediteurDto = messageDto.getExpediteurDto();
            adrSender = adresseDao.findAdresseById(expediteurDto.getId());
            if (adrSender == null) {
                adrSender = new Adresse();
                adrSender.setIdADRESSE(expediteurDto.getId());
                if (expediteurDto.getPersonneDto() != null) {
                    Personne personne = new Personne();
                    personne.setIdPERSONNE(expediteurDto.getPersonneDto().getId());
                    personne.setNom(expediteurDto.getPersonneDto().getNom());
                    personne.setPrenom(expediteurDto.getPersonneDto().getPrenom());
                    personne.setAdresse(adrSender);

                    if (expediteurDto.getPersonneDto().getFonctionDtoList() != null) {
                        List< PersonneFonction> pfList = new ArrayList<>();
                        for (FonctionDto fDto : expediteurDto.getPersonneDto().getFonctionDtoList()) {
                            Fonction fonction = fonctionDao.findFonctionByTitle(fDto.getTitle());
                            if (fonction == null) {
                                fonction = new Fonction();
                                fonction.setTitle(fDto.getTitle());
                            }
                            PersonneFonction pf = new PersonneFonction();
                            pf.setPersonne(personne);
                            pf.setFonction(fonction);
                            pfList.add(pf);
                        }
                        personne.setPersonneFonctionList(pfList);
                    }

                    adrSender.setPersonne(personne);
                } //fait par moi
                else {
                    Liste liste = new Liste();
                    liste.setIdLISTE(expediteurDto.getListeDto().getId());
                    liste.setLibelle(expediteurDto.getListeDto().getLibelle());
                    liste.setAdresse(adrSender);

                }

            }
            message.setSender(adrSender);
        }

        if (messageDto.getDestinataireDtoList() != null) {
            List<Adresse> destList = new ArrayList<>();
            for (AdresseDto destDto : messageDto.getDestinataireDtoList()) {
                if (destDto.getId().equalsIgnoreCase(adrSender.getIdADRESSE())) {
                    destList.add(adrSender);
                } else {
                    Adresse adr = adresseDao.findAdresseById(destDto.getId());
                    if (adr == null) {
                        adr = new Adresse();
                        adr.setIdADRESSE(destDto.getId());
                        if (destDto.getPersonneDto() != null) {
                            Personne personne = new Personne();
                            personne.setIdPERSONNE(destDto.getPersonneDto().getId());
                            personne.setNom(destDto.getPersonneDto().getNom());
                            personne.setPrenom(destDto.getPersonneDto().getPrenom());
                            personne.setAdresse(adr);
                            adr.setPersonne(personne);
                        } //fait par moi
                        else {
                            Liste liste = new Liste();
                            liste.setIdLISTE(destDto.getListeDto().getId());
                            liste.setLibelle(destDto.getListeDto().getLibelle());
                            liste.setAdresse(adr);
                            adr.setListe(liste);
                        }
                    }
                    destList.add(adr);
                }

            }

            message.setDestinataires(destList);
        }

        if (messageDto.getDestinataireCopieDtoList() != null) {
            List<Adresse> destCCList = new ArrayList<>();
            for (AdresseDto destCc : messageDto.getDestinataireCopieDtoList()) {
                if (destCc.getId().equalsIgnoreCase(adrSender.getIdADRESSE())) {
                    destCCList.add(adrSender);
                } else {
                    Adresse adr = adresseDao.findAdresseById(destCc.getId());
                    if (adr == null) {
                        adr = new Adresse();
                        adr.setIdADRESSE(destCc.getId());
                        if (destCc.getPersonneDto() != null) {
                            Personne pers = new Personne();
                            pers.setIdPERSONNE(destCc.getPersonneDto().getId());
                            pers.setPrenom(destCc.getPersonneDto().getPrenom());
                            pers.setNom(destCc.getPersonneDto().getNom());
                            pers.setAdresse(adr);
                            adr.setPersonne(pers);
                        } //fait par moi
                        else {
                            Liste liste = new Liste();
                            liste.setIdLISTE(destCc.getListeDto().getId());
                            liste.setLibelle(destCc.getListeDto().getLibelle());
                            liste.setAdresse(adr);
                            adr.setListe(liste);
                        }
                    }
                    destCCList.add(adr);
                }

            }
            message.setDestinatairesCopie(destCCList);
        }
        if (messageDto.getTransfertDtoList() != null) {
            List<Adresse> transfertList = new ArrayList<>();
            for (AdresseDto transfert : messageDto.getTransfertDtoList()) {
                if (transfert.getId().equalsIgnoreCase(adrSender.getIdADRESSE())) {
                    transfertList.add(adrSender);
                } else {
                    Adresse adr = adresseDao.findAdresseById(transfert.getId());
                    if (adr == null) {
                        adr = new Adresse();
                        adr.setIdADRESSE(transfert.getId());
                        if (transfert.getPersonneDto() != null) {
                            Personne personne = new Personne();
                            personne.setIdPERSONNE(transfert.getPersonneDto().getId());
                            personne.setPrenom(transfert.getPersonneDto().getPrenom());
                            personne.setNom(transfert.getPersonneDto().getNom());
                            personne.setAdresse(adr);
                            adr.setPersonne(personne);
                        } else {
                            Liste liste = new Liste();
                            liste.setIdLISTE(transfert.getListeDto().getId());
                            liste.setLibelle(transfert.getListeDto().getLibelle());
                            liste.setAdresse(adr);
                            adr.setListe(liste);
                        }
                    }
                     transfertList.add(adr);
                }
            }
              message.setAdresseTransfertList(transfertList);
        }

        if (messageDto.getFichierDtoList() != null) {
            List<Fichier> fichierList = new ArrayList<>();
            for (FichierDto fichierDto : messageDto.getFichierDtoList()) {
                Fichier fich = new Fichier();
                fich.setFilename(fichierDto.getFilename());
                fich.setFilepath(fichierDto.getFilepath());
                fich.setFiletype(fichierDto.getFiletype());
                fich.setMessageID(message);
                fichierList.add(fich);
            }
            message.setFichierList(fichierList);
        }

        return message;

    }

    /**
     * Convertir un DTO en entity
     *
     * @param message
     * @return
     */
    private MessageDto convertToDto(Message message) {

        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getIdMESSAGE());
        messageDto.setDate(message.getSentdate());
        messageDto.setObject(message.getSubject());
        messageDto.setBody(message.getBody());
        if (message.getSender() != null) {
            Adresse adr = message.getSender();
            AdresseDto adrDto = new AdresseDto();
            adrDto.setId(adr.getIdADRESSE());

            if (adr.getPersonne() != null) {
                PersonneDto persDto = new PersonneDto();
                persDto.setId(adr.getPersonne().getIdPERSONNE());
                persDto.setNom(adr.getPersonne().getNom());
                persDto.setPrenom(adr.getPersonne().getPrenom());
                if (adr.getPersonne().getPersonneFonctionList() != null) {
                    List<FonctionDto> fonctDtoList = new ArrayList<>();
                    for (PersonneFonction pf : adr.getPersonne().getPersonneFonctionList()) {
                        FonctionDto fDto = new FonctionDto();
                        fDto.setId(pf.getFonction().getIdFONCTION());
                        fDto.setTitle(pf.getFonction().getTitle());
                        fonctDtoList.add(fDto);
                    }
                    persDto.setFonctionDtoList(fonctDtoList);
                }
                adrDto.setPersonneDto(persDto);
            } else if (adr.getListe() != null) {
                ListeDto listeDto = new ListeDto();
                listeDto.setId(adr.getListe().getIdLISTE());
                listeDto.setLibelle(adr.getListe().getLibelle());
                adrDto.setListeDto(listeDto);
            }
            messageDto.setExpediteurDto(adrDto);
        }

        //Convertion destinataire liste
        if (message.getDestinataires() != null) {
            List<AdresseDto> destDtoList = new ArrayList<>();
            for (Adresse dest : message.getDestinataires()) {
                AdresseDto adrDto = new AdresseDto();
                adrDto.setId(dest.getIdADRESSE());
                if (dest.getPersonne() != null) {
                    PersonneDto persDto = new PersonneDto();
                    persDto.setId(dest.getPersonne().getIdPERSONNE());
                    persDto.setNom(dest.getPersonne().getNom());
                    persDto.setPrenom(dest.getPersonne().getPrenom());
                    adrDto.setPersonneDto(persDto);

                } else if (dest.getListe() != null) {
                    ListeDto listeDto = new ListeDto();
                    listeDto.setId(dest.getListe().getIdLISTE());
                    listeDto.setLibelle(dest.getListe().getLibelle());
                    adrDto.setListeDto(listeDto);
                }

                destDtoList.add(adrDto);
            }
            messageDto.setDestinataireDtoList(destDtoList);
        }

        //Conversion destinataire Copie
        if (message.getDestinatairesCopie() != null) {
            List<AdresseDto> destDtoList = new ArrayList<>();
            for (Adresse dest : message.getDestinatairesCopie()) {
                AdresseDto adrDto = new AdresseDto();
                adrDto.setId(dest.getIdADRESSE());
                if (dest.getPersonne() != null) {
                    PersonneDto persDto = new PersonneDto();
                    persDto.setId(dest.getPersonne().getIdPERSONNE());
                    persDto.setNom(dest.getPersonne().getNom());
                    persDto.setPrenom(dest.getPersonne().getPrenom());
                    adrDto.setPersonneDto(persDto);

                } else if (dest.getListe() != null) {
                    ListeDto listeDto = new ListeDto();
                    listeDto.setId(dest.getListe().getIdLISTE());
                    listeDto.setLibelle(dest.getListe().getLibelle());
                    adrDto.setListeDto(listeDto);
                }

                destDtoList.add(adrDto);
            }
            messageDto.setDestinataireDtoList(destDtoList);
        }

        //Conversion fichier
        if (message.getFichierList() != null) {
            List<FichierDto> fichierDtoList = new ArrayList<>();
            for (Fichier fichier : message.getFichierList()) {
                FichierDto fichDto = new FichierDto();
                fichDto.setId(fichier.getIdFICHIER());
                fichDto.setFilename(fichier.getFilename());
                fichDto.setFiletype(fichier.getFiletype());
                fichDto.setFilepath(fichier.getFilepath());
                fichierDtoList.add(fichDto);
            }
            messageDto.setFichierDtoList(fichierDtoList);
        }

        return messageDto;
    }

    /**
     *
     * @param dir
     * @throws MessagingException
     * @throws IOException
     */
    private List<String> insertAllFiles(String dir) throws MessagingException, IOException {
        List<String> insertedFileList = new ArrayList<>();
        List<File> fichiers = FileScan.listAllFileFromDir(dir);
        for (File file : fichiers) {
            String fileName = file.getAbsolutePath();
            System.out.println("Fichier " + fileName);
            MailObject mailObject = MimeMessageReader.readMessageFile(fileName);

            Message message = new Message();

            message.setSubject(mailObject.getSubject());
            message.setSentdate(mailObject.getSentDate());
            message.setBody(mailObject.getContent());
            Adresse expediteur = adresseDao.findAdresseById(mailObject.getFrom().getMail());
            if (expediteur == null) {
                expediteur = new Adresse();
                expediteur.setIdADRESSE(mailObject.getFrom().getMail());

                if (mailObject.getFrom().getLastName() != null || mailObject.getFrom().getFirstName() != null) {
                    Personne personne = new Personne();
                    personne.setIdPERSONNE(mailObject.getFrom().getMail());
                    personne.setNom(mailObject.getFrom().getLastName());
                    personne.setPrenom(mailObject.getFrom().getFirstName());
                    personne.setAdresse(expediteur);

                    if (mailObject.getFonction() != null) {
                        List< PersonneFonction> pfList = new ArrayList<>();
                        Fonction fonction = fonctionDao.findFonctionByTitle(mailObject.getFonction());
                        if (fonction == null) {
                            fonction = new Fonction();
                            fonction.setTitle(mailObject.getFonction());
                        }

                        PersonneFonction personneFonction = new PersonneFonction();

                        personneFonction.setFonction(fonction);
                        personneFonction.setPersonne(personne);

                        pfList.add(personneFonction);

                        personne.setPersonneFonctionList(pfList);

                    }
                    expediteur.setPersonne(personne);
                } else {
                    // LISTE => A faire !!!
                    Liste list = new Liste();
                    list.setIdLISTE(mailObject.getFrom().getMail());
                    list.setAdresse(expediteur);
                    expediteur.setListe(list);

                }
            }
            message.setSender(expediteur);

            // Ajout destinataires TO
            List<InfoPersonne> destinataires = mailObject.getTo();
            List<Adresse> destinatairesList = new ArrayList<>();
            for (InfoPersonne info : destinataires) {
                if (info.getMail().equalsIgnoreCase(expediteur.getIdADRESSE())) {
                    destinatairesList.add(expediteur);
                } else {

                    Adresse adresse = adresseDao.findAdresseById(info.getMail());
                    if (adresse == null) {
                        adresse = new Adresse();
                        adresse.setIdADRESSE(info.getMail());
                        if (info.getLastName() != null || info.getFirstName() != null) {
                            Personne personne = new Personne();
                            personne.setIdPERSONNE(info.getMail());
                            personne.setNom(info.getLastName());
                            personne.setPrenom(info.getFirstName());
                            personne.setAdresse(adresse);
                            adresse.setPersonne(personne);

                        } else {
                            Liste list = new Liste();
                            list.setIdLISTE(info.getMail());
                            list.setAdresse(adresse);
                            adresse.setListe(list);
                        }
                    }
                    destinatairesList.add(adresse);
                }
            }
            message.setDestinataires(destinatairesList);

            // Ajout destinataires CC
            List<InfoPersonne> listPers = mailObject.getCc();
            List<Adresse> adrList = new ArrayList<>();
            for (InfoPersonne info : listPers) {
                if (info.getMail().equalsIgnoreCase(expediteur.getIdADRESSE())) {
                    destinatairesList.add(expediteur);
                } else {
                    Adresse adr = adresseDao.findAdresseById(info.getMail());
                    if (adr == null) {
                        adr = new Adresse();
                        adr.setIdADRESSE(info.getMail());
                        if (info.getFirstName() != null || info.getLastName() != null) {
                            Personne pers = new Personne();
                            pers.setIdPERSONNE(info.getMail());
                            pers.setNom(info.getLastName());
                            pers.setPrenom(info.getFirstName());
                            pers.setAdresse(adr);
                            adr.setPersonne(pers);
                        } //fait par moi 
                        else {
                            Liste liste = new Liste();
                            liste.setIdLISTE(info.getMail());
                            liste.setAdresse(adr);
                            adr.setListe(liste);
                        }
                    }
                    adrList.add(adr);
                }
            }
            message.setDestinatairesCopie(adrList);

            List<Fichier> fichierList = new ArrayList<>();
            if (mailObject.getFileList() != null) {
                List<AttachFile> fileList = mailObject.getFileList();
                for (AttachFile af : fileList) {
                    Fichier fichier = new Fichier();
                    fichier.setFilename(af.getFilename());
                    fichier.setFilepath(af.getFilepath());
                    fichier.setFiletype(af.getFiletype());
                    fichier.setMessageID(message);
                    fichierList.add(fichier);
                }
            }
            message.setFichierList(fichierList);
            messageDao.insertMessage(message);
            insertedFileList.add(fileName);

        }
        return insertedFileList;
    }

}
