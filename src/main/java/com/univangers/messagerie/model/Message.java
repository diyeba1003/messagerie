/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

/**
 *
 * @author etud
 */
@Entity
@Table(name = "MESSAGE")
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findByIdMESSAGE", query = "SELECT m FROM Message m WHERE m.idMESSAGE = :idMESSAGE"),
    @NamedQuery(name = "Message.findBySentdate", query = "SELECT m FROM Message m WHERE m.sentdate = :sentdate"),
    @NamedQuery(name = "Message.findBySubject", query = "SELECT m FROM Message m WHERE m.subject = :subject")})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    @Getter
    @Setter
    private Integer idMESSAGE;

    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date sentdate;

    @Column(length = 200)
    @Getter
    @Setter
    private String subject;  
    
    @Lob
    @Column(name = "body", length = 65535)
    @Type(type="text")
    @Getter
    @Setter
    private String body;

    @JoinColumn(name = "sender", referencedColumnName = "idADRESSE", nullable = false)
    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false, fetch = FetchType.LAZY)
    private Adresse sender;

    @JoinTable(name = "DESTINATAIRE", joinColumns = {
        @JoinColumn(name = "MESSAGE_ID", referencedColumnName = "idMESSAGE", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ADRESSE_ID", referencedColumnName = "idADRESSE", nullable = false)})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Adresse> destinataires;

    @JoinTable(name = "DESTINATAIRE_COPIE", joinColumns = {
        @JoinColumn(name = "MESSAGE_ID", referencedColumnName = "idMESSAGE", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ADRESSE_ID", referencedColumnName = "idADRESSE", nullable = false)})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Adresse> destinatairesCopie;

    @JoinTable(name = "TRANSFERT", joinColumns = {
        @JoinColumn(name = "MESSAGE_ID", referencedColumnName = "idMESSAGE", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ADRESSE_ID", referencedColumnName = "idADRESSE", nullable = false)})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Adresse> adresseTransfertList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "messageID", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Fichier> fichierList;

    @JoinTable(name = "MESSAGE_REPLY", joinColumns = {
        @JoinColumn(name = "MESSAGE_REPLY_ID", referencedColumnName = "idMESSAGE", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "MESSAGE_ID", referencedColumnName = "idMESSAGE", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Message> messageReplyList;

    public Message() {
    }

    public Message(Integer idMESSAGE) {
        this.idMESSAGE = idMESSAGE;
    }

    public Message(Integer idMESSAGE, Date sentdate) {
        this.idMESSAGE = idMESSAGE;
        this.sentdate = sentdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMESSAGE != null ? idMESSAGE.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.idMESSAGE == null && other.idMESSAGE != null) || (this.idMESSAGE != null && !this.idMESSAGE.equals(other.idMESSAGE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.univangers.messagerie.model.Message[ idMESSAGE=" + idMESSAGE + " ]";
    }

}
