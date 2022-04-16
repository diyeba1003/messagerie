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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author etud
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "id_adresse", nullable = false, length = 80)
    @Getter
    @Setter
    private String idAdresse;

    @JoinTable(name = "transfert", joinColumns = {
        @JoinColumn(name = "id_adresse", referencedColumnName = "id_adresse", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "id_message", referencedColumnName = "id_message", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Message> messagesTranfertList;

    @JoinTable(name = "destinataire", joinColumns = {
        @JoinColumn(name = "id_adresse", referencedColumnName = "id_adresse", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "id_message", referencedColumnName = "id_message", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Message> messagesDestinataire;

    @JoinTable(name = "destinataire_copie", joinColumns = {
        @JoinColumn(name = "id_adresse_copie", referencedColumnName = "id_adresse", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "id_message_copie", referencedColumnName = "id_message", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Message> messagesDestinataireCopie;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "expediteur", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Message> messageExpediteurList;

    public Adresse() {
    }

    public Adresse(String idAdresse) {
        this.idAdresse = idAdresse;
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
