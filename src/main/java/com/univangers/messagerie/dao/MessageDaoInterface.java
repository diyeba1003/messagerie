/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Message;
import java.util.List;

/**
 *
 * @author etud
 */
public interface MessageDaoInterface {
    public void insertMessage(Message message);
    public Message findMessageById(Integer idMessage);
    public List<Message> findAllMessage();
    public Integer countMessage();
    public void updateMessage(Message message);
    public void deleteMessage(Integer idMessage);
    
}
