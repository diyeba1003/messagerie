/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Message;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
        List<Message> messageList  = em.createQuery("SELECT m FROM Message m").getResultList();

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

    @Override
    public Integer countMessage() {
        Integer count = 0;
        Object object = em.createQuery("SELECT COUNT(m)  FROM Message m ").getSingleResult();
        if (object != null) {
            count = (int) (long) object;
        }
        return count;
    }

    @Override
    public List<Message> findMessageBySender(String idAdresse) {
        List<Message> messageList;
        try {
            messageList = em.createQuery("SELECT m FROM Message m WHERE m.sender.idADRESSE=:idAdresse")
                    .setParameter("idAdresse", idAdresse).getResultList();
        } catch (NoResultException nre) {
            messageList = new ArrayList<>();
        }
        return messageList;
    }

    @Override
    public List<Message> findMessageBySubject(String keyWord) {
        List<Message> messageList;
        try{
            messageList=em.createQuery("SELECT m FROM Message m WHERE LOWER(m.subject) LIKE LOWER(CONCAT( '%',:keyWord,'%'))")
                    .setParameter("keyWord", keyWord).getResultList();
        }catch (NoResultException nre) {
            messageList = new ArrayList<>();
        }
        return messageList;
    }

    @Override
    public List<Message> findMessageByDestinataire(String keyWord) {
            List<Message> messageList;
            try{
                messageList=em.createQuery("SELECT m FROM Message m WHERE m.destinataires=:keyWord").setParameter("keyWord", keyWord).getResultList();
            }catch(NoResultException nre){
                messageList=new ArrayList<>();
                
            }
            return messageList;
    }
      
    

    @Override
    public List<Message> findAllMessageBetweenDate() {
        List<Message> messageList=new ArrayList<>();
        messageList = em.createQuery("SELECT m FROM Message m WHERE sentdate BETWEEN 2010-06-07 AND 2010-12-07").getResultList();
        return messageList;
    }

}
