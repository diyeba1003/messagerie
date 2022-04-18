/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author etud
 */
@Entity
@Table(name="LISTE")
@NamedQueries({
    @NamedQuery(name = "Liste.findAll", query = "SELECT l FROM Liste l"),
    @NamedQuery(name = "Liste.findByIdLISTE", query = "SELECT l FROM Liste l WHERE l.idLISTE = :idLISTE"),
    @NamedQuery(name = "Liste.findByLibelle", query = "SELECT l FROM Liste l WHERE l.libelle = :libelle")})
public class Liste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 80)
     @Getter @Setter
    private String idLISTE;
    
    @Column(length = 120)
     @Getter @Setter
    private String libelle;
    
    @JoinColumn(name = "idLISTE", referencedColumnName = "idADRESSE", nullable = false, insertable = false, updatable = false)
     @Getter @Setter
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Adresse adresse;

    public Liste() {
    }

    public Liste(String idLISTE) {
        this.idLISTE = idLISTE;
    }

    public String getIdLISTE() {
        return idLISTE;
    }

    public void setIdLISTE(String idLISTE) {
        this.idLISTE = idLISTE;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLISTE != null ? idLISTE.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Liste)) {
            return false;
        }
        Liste other = (Liste) object;
        if ((this.idLISTE == null && other.idLISTE != null) || (this.idLISTE != null && !this.idLISTE.equals(other.idLISTE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.univangers.messagerie.model.Liste[ idLISTE=" + idLISTE + " ]";
    }
    
}
