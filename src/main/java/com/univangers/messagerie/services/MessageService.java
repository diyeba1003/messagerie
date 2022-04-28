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
import com.univangers.messagerie.dto.ListeDto;
import com.univangers.messagerie.dto.MessageDto;
import com.univangers.messagerie.dto.PersonneDto;
import com.univangers.messagerie.dto.PersonneFonctionDto;
import com.univangers.messagerie.model.Adresse;
import com.univangers.messagerie.model.Fichier;
import com.univangers.messagerie.model.Fonction;
import com.univangers.messagerie.model.Liste;
import com.univangers.messagerie.model.Message;
import com.univangers.messagerie.model.Personne;
import com.univangers.messagerie.model.PersonneFonction;
import java.util.ArrayList;
import java.util.List;
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

                    if (expediteurDto.getPersonneDto().getPersonneFonctionDtoList() != null) {
                        List< PersonneFonction> pfList = new ArrayList<>();
                        for (PersonneFonctionDto pfDto : expediteurDto.getPersonneDto().getPersonneFonctionDtoList()) {
                            Fonction fonction = fonctionDao.findFonctionByTitle(pfDto.getFonctionDto().getTitle());
                            if (fonction == null) {
                                fonction = new Fonction();
                                fonction.setTitle(pfDto.getFonctionDto().getTitle());
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
                if (destDto.getId().equals(adrSender.getIdADRESSE())) {
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
                if (destCc.getId().equals(adrSender.getIdADRESSE())) {
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
                adrDto.setPersonneDto(persDto);
            } //fait par moi
            else {
                ListeDto listeDto = new ListeDto();
                listeDto.setId(adr.getListe().getIdLISTE());
                listeDto.setLibelle(adr.getListe().getLibelle());
                adrDto.setListeDto(listeDto);
            }
            messageDto.setExpediteurDto(adrDto);
        }
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

                } //fait par moi
                else {
                    ListeDto listeDto = new ListeDto();
                    listeDto.setId(dest.getListe().getIdLISTE());
                    listeDto.setLibelle(dest.getListe().getLibelle());
                    adrDto.setListeDto(listeDto);
                }

                destDtoList.add(adrDto);
            }
            messageDto.setDestinataireDtoList(destDtoList);
        }

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

                } //fait par moi
                else {
                    ListeDto listeDto = new ListeDto();
                    listeDto.setId(dest.getListe().getIdLISTE());
                    listeDto.setLibelle(dest.getListe().getLibelle());
                    adrDto.setListeDto(listeDto);
                }

                destDtoList.add(adrDto);
            }
            messageDto.setDestinataireDtoList(destDtoList);
        }

        return messageDto;
    }

}
