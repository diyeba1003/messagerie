/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Message;
import java.util.Date;
import java.util.List;

/**
 *
 * @author etud
 */
public interface MessageDaoInterface {

    public void insertMessage(Message message);

    public Message findMessageById(Integer idMessage);

    public List<Message> findAllMessage();

    public List<Message> findMessageBySender(String idAdresse);

    public Integer countMessage();

    public void updateMessage(Message message);

    public void deleteMessage(Integer idMessage);

    public List<Message> findMessageBySubject(String keyWord);

    public List<Message> findMessageByDestinataire(String keyWord);

    public Integer countMessagesBetweenDates(Date startDate, Date endDate);

    public Integer countMessageById(Integer idMessage);

    public List<Message> findMessagesBetweenDates(Date startDate, Date endDate);

  
}
