/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dto.MessageDto;
import java.util.List;

/**
 *
 * @author etud
 */
public interface MessageServiceInterface {
    
    public void insertMessageDto(MessageDto messageDto);
    public MessageDto findMessageDtoById(Integer id);
    public List<MessageDto> findAllMessageDto();
    public void updateMessageDto(MessageDto messageDto);
    public void deleteMessageDto(Integer id);
    
}
