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
    private Integer idMessage;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "object", length = 200)
    private String object;
    @Column(name = "body", length = 2000)
    private String body;
    @ManyToMany(mappedBy = "messageList", fetch = FetchType.LAZY)
    private List<Adresse> adresseList;
    @ManyToMany(mappedBy = "messageList1", fetch = FetchType.LAZY)
    private List<Adresse> adresseList1;
    @ManyToMany(mappedBy = "messageList2", fetch = FetchType.LAZY)
    private List<Adresse> adresseList2;
    @JoinColumn(name = "expediteur", referencedColumnName = "id_adresse", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
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

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Adresse> getAdresseList() {
        return adresseList;
    }

    public void setAdresseList(List<Adresse> adresseList) {
        this.adresseList = adresseList;
    }

    public List<Adresse> getAdresseList1() {
        return adresseList1;
    }

    public void setAdresseList1(List<Adresse> adresseList1) {
        this.adresseList1 = adresseList1;
    }

    public List<Adresse> getAdresseList2() {
        return adresseList2;
    }

    public void setAdresseList2(List<Adresse> adresseList2) {
        this.adresseList2 = adresseList2;
    }

    public Adresse getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Adresse expediteur) {
        this.expediteur = expediteur;
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
