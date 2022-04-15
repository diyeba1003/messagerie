/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author etud
 */
@Entity
@Table(name = "adresse", catalog = "messagerie", schema = "")
@NamedQueries({
    @NamedQuery(name = "Adresse.findAll", query = "SELECT a FROM Adresse a"),
    @NamedQuery(name = "Adresse.findByIdAdresse", query = "SELECT a FROM Adresse a WHERE a.idAdresse = :idAdresse")})
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_adresse", nullable = false, length = 80)
    private String idAdresse;
    @JoinTable(name = "transfert", joinColumns = {
        @JoinColumn(name = "id_adresse", referencedColumnName = "id_adresse", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "id_message", referencedColumnName = "id_message", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Message> messageList;
    @JoinTable(name = "destinataire", joinColumns = {
        @JoinColumn(name = "id_adresse", referencedColumnName = "id_adresse", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "id_message", referencedColumnName = "id_message", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Message> messageList1;
    @JoinTable(name = "destinataire_copie", joinColumns = {
        @JoinColumn(name = "id_adresse_copie", referencedColumnName = "id_adresse", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "id_message_copie", referencedColumnName = "id_message", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Message> messageList2;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "adresse", fetch = FetchType.LAZY)
    private Liste liste;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "adresse", fetch = FetchType.LAZY)
    private PersonnePhysique personnePhysique;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "expediteur", fetch = FetchType.LAZY)
    private List<Message> messageList3;

    public Adresse() {
    }

    public Adresse(String idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(String idAdresse) {
        this.idAdresse = idAdresse;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<Message> getMessageList1() {
        return messageList1;
    }

    public void setMessageList1(List<Message> messageList1) {
        this.messageList1 = messageList1;
    }

    public List<Message> getMessageList2() {
        return messageList2;
    }

    public void setMessageList2(List<Message> messageList2) {
        this.messageList2 = messageList2;
    }

    public Liste getListe() {
        return liste;
    }

    public void setListe(Liste liste) {
        this.liste = liste;
    }

    public PersonnePhysique getPersonnePhysique() {
        return personnePhysique;
    }

    public void setPersonnePhysique(PersonnePhysique personnePhysique) {
        this.personnePhysique = personnePhysique;
    }

    public List<Message> getMessageList3() {
        return messageList3;
    }

    public void setMessageList3(List<Message> messageList3) {
        this.messageList3 = messageList3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdresse != null ? idAdresse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.idAdresse == null && other.idAdresse != null) || (this.idAdresse != null && !this.idAdresse.equals(other.idAdresse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.univangers.messagerie.model.Adresse[ idAdresse=" + idAdresse + " ]";
    }
    
}
