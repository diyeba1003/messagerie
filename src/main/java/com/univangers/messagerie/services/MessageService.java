/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dao.AdresseDaoInterface;
import com.univangers.messagerie.dao.MessageDaoInterface;
import com.univangers.messagerie.dto.AdresseDto;
import com.univangers.messagerie.dto.MessageDto;
import com.univangers.messagerie.model.Adresse;
import com.univangers.messagerie.model.Message;
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
        if (messageDto.getExpediteurDto() != null) {
            Adresse adr = adresseDao.findAdresseById(messageDto.getExpediteurDto().getId());
            if (adr == null) {
                adr = new Adresse();
                adr.setIdADRESSE(messageDto.getExpediteurDto().getId());
            }
            message.setSender(adr);
        }

        if (messageDto.getDestinataireDtoList() != null) {
            List<Adresse> destList = new ArrayList<>();
            for (AdresseDto destDto : messageDto.getDestinataireDtoList()) {
                Adresse adrDto = adresseDao.findAdresseById(destDto.getId());
                if (adrDto == null) {
                    adrDto = new Adresse();
                    adrDto.setIdADRESSE(destDto.getId());
                }
                destList.add(adrDto);
            }
            message.setDestinataires(destList);
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
            AdresseDto adrDto = new AdresseDto();
            adrDto.setId(message.getSender().getIdADRESSE());
            messageDto.setExpediteurDto(adrDto);
        }

        return messageDto;
    }

}
