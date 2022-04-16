/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Message;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author etud
 */
@Repository
@Transactional
public class MessageDao implements MessageDaoInterface {

    @PersistenceContext
    private transient EntityManager em;

    @Override
    public void insertMessage(Message message) {
        em.persist(message); // Fait le INSERT
        em.flush();
    }

    @Override
    public Message findMessageById(Integer idMessage) {

        Message m = em.find(Message.class, idMessage);
        return m;
    }

    @Override
    public List<Message> findAllMessage() {
        List<Message> messageList = new ArrayList<>();

        messageList = em.createQuery("SELECT m FROM Message m").getResultList();

        return messageList;
    }

    @Override
    public void updateMessage(Message message) {
        em.merge(message);
    }

    @Override
    public void deleteMessage(Integer idMessage) {
        Message mToDelete = findMessageById(idMessage);
        if (mToDelete != null) {
            em.remove(mToDelete);
        }
    }

}
