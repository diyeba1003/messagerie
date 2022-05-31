/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dto.AdresseDto;
import com.univangers.messagerie.dto.MessageDto;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public Integer countMessagesDtoBetweenDates(Date startDate, Date endDate);

    public List<MessageDto> findMessageDtoBySender(String senderId, Boolean isSearch);

    public List<MessageDto> findMessageDtoBySubject(String keyWord);

    public List<MessageDto> findMessageDtoByDestinataire(String keyWord, Boolean isSearch);

    public List<MessageDto> findMessageDtoByDestinataireCc(String keyWord, Boolean isSearch);

    public Integer countMessageDtoById(Integer idMessage);

    public List<MessageDto> findMessagesDtoBetweenDates(Date startDate, Date endDate);
    
    public List<AdresseDto> getContactsFrom(String idContact);
    
    public List<AdresseDto> getContactsTo(String idContact);
    
    public List<AdresseDto> getContactsCc(String idContact);
    

    public Map getStatPerMonth(Date startDate, Date endDate);

}
