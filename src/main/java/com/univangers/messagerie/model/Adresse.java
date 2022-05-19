/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author etud
 */
@Entity
@Table(name = "ADRESSE")
@NamedQueries({
    @NamedQuery(name = "Adresse.findAll", query = "SELECT a FROM Adresse a"),
    @NamedQuery(name = "Adresse.findByIdADRESSE", query = "SELECT a FROM Adresse a WHERE a.idADRESSE = :idADRESSE")})
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 80)
    @Getter
    @Setter
    private String idADRESSE;

    @ManyToMany(mappedBy = "adresseTransfertList", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Message> messageTransfertList;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Message> messageList;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "adresse", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Liste liste;

    @ManyToMany(mappedBy = "destinataires", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Message> destinataireMessageList;

    @ManyToMany(mappedBy = "destinatairesCopie", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Message> destinatairesCopieMessageList;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "adresse", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Personne personne;
    
     @JoinTable(name = "ADRESSE_CONTACT", joinColumns = {
        @JoinColumn(name = "ADRESSE_ID", referencedColumnName = "idADRESSE", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "ADRESSE_CONTACT_ID", referencedColumnName = "idADRESSE", nullable = false)})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<Adresse> adresseContactList = new ArrayList<>();


    public Adresse() {
    }

    public Adresse(String idADRESSE) {
        this.idADRESSE = idADRESSE;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idADRESSE != null ? idADRESSE.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.idADRESSE == null && other.idADRESSE != null) || (this.idADRESSE != null && !this.idADRESSE.equalsIgnoreCase(other.idADRESSE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.univangers.messagerie.model.Adresse[ idADRESSE=" + idADRESSE + " ]";
    }

}
