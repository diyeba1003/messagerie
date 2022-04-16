/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author etud
 */
@Entity
@Table(name = "message", catalog = "messagerie", schema = "")
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findByIdMessage", query = "SELECT m FROM Message m WHERE m.idMessage = :idMessage"),
    @NamedQuery(name = "Message.findByDate", query = "SELECT m FROM Message m WHERE m.date = :date"),
    @NamedQuery(name = "Message.findByObject", query = "SELECT m FROM Message m WHERE m.object = :object"),
    @NamedQuery(name = "Message.findByBody", query = "SELECT m FROM Message m WHERE m.body = :body")})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_message", nullable = false)
    @Getter
    @Setter
    private Integer idMessage;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date date;
    @Column(name = "object", length = 200)
    
    @Getter
    @Setter
    private String object;
    @Column(name = "body", length = 2000)
    @Getter
    @Setter
    private String body;
    
    @ManyToMany(mappedBy = "messagesTranfertList", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Adresse> adrTransfertList;
    
    @ManyToMany(mappedBy = "messagesDestinataire", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Adresse> adrDestinataireList;
    
    @ManyToMany(mappedBy = "messagesDestinataireCopie", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Adresse> adrDestinataireCopieList;
    
    @JoinColumn(name = "expediteur", referencedColumnName = "id_adresse", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Adresse expediteur;

    public Message() {
    }

    public Message(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public Message(Integer idMessage, Date date) {
        this.idMessage = idMessage;
        this.date = date;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMessage != null ? idMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.idMessage == null && other.idMessage != null) || (this.idMessage != null && !this.idMessage.equals(other.idMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.univangers.messagerie.model.Message[ idMessage=" + idMessage + " ]";
    }
    
}
