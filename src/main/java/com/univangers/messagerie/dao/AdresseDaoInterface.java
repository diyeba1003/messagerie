/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Adresse;
import java.util.List;

/**
 *
 * @author etud
 */  
public interface AdresseDaoInterface  {
    
    public void insertAdresse(Adresse adresse);
    
    public Adresse findAdresseById(String idFonction);
    
    public Integer countAdresse();
    
    public List<Adresse> findAllAdresse();
    
    public void update(Adresse adresse);
        
    public Boolean adresseHasContact(String idADRESSE, String contact);
    
    public Integer countNbExchangeMessageByDestinataireWithSender(String senderAdress, String destAdress);
    
    public Integer countNbExchangeMessageByDestinataireCopieWithSender(String senderAdress, String dcAdress);
}
