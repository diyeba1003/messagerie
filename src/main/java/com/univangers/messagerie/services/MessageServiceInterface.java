/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dto.MessageDto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author etud
 */
public interface MessageServiceInterface {

    public void insertMessageDto(MessageDto messageDto);

    public List<String> insertAll(String rep);

    public MessageDto findMessageDtoById(Integer id);

    public List<MessageDto> findAllMessageDto();

    public Integer countMessageDto();

    public void updateMessageDto(MessageDto messageDto);

    public void deleteMessageDto(Integer id);

    public Integer countMessagesDtoBetweenDates(Date startDate, Date endDate);

    public List<MessageDto> findMessageDtoBySender(String senderId);

    public List<MessageDto> findMessageDtoBySubject(String keyWord);

    public List<MessageDto> findMessageDtoByDestinataire(String keyWord);

    public List<MessageDto> findMessagesDtoBetweenDates(Date startDate, Date endDate);
}
