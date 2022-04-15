/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dao.MessageDaoInterface;
import com.univangers.messagerie.dto.MessageDto;
import com.univangers.messagerie.model.Message;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author etud
 */
@Service
public class MessageService  implements MessageServiceInterface{
    
    @Autowired
    private MessageDaoInterface messageDao;

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
        if(!messageList.isEmpty()){
            for(Message m: messageList){
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
    private Message convertToEntity(MessageDto messageDto){
        Message message = new Message();
        message.setIdMessage(messageDto.getId());
        message.setDate(messageDto.getDate());
        message.setObject(messageDto.getObject());
        message.setBody(messageDto.getBody());
        
        return message;
    }
    
    /**
     * Convertir un DTO en entity
     * 
     * @param message
     * @return 
     */
    private MessageDto convertToDto(Message message){
        
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getIdMessage());
        messageDto.setDate(message.getDate());
        messageDto.setObject(message.getObject());
        messageDto.setBody(message.getBody());
        
        return messageDto;
    }
    
}
