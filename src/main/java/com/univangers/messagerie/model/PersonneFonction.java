/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "PERSONNE_FONCTION")
@NamedQueries({
    @NamedQuery(name = "PersonneFonction.findAll", query = "SELECT p FROM PersonneFonction p"),
    @NamedQuery(name = "PersonneFonction.findByStartdate", query = "SELECT p FROM PersonneFonction p WHERE p.startdate = :startdate"),
    @NamedQuery(name = "PersonneFonction.findByEnddate", query = "SELECT p FROM PersonneFonction p WHERE p.enddate = :enddate")})
public class PersonneFonction implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @JoinColumn(name = "FONCTION_ID", referencedColumnName = "idFONCTION", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter @Setter
    private Fonction fonction;
    
    @Id
    @JoinColumn(name = "PERSONNE_ID", referencedColumnName = "idPERSONNE", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter @Setter
    private Personne personne;
    
    @Temporal(TemporalType.DATE)
    @Getter @Setter
    private Date startdate;
    
    @Temporal(TemporalType.DATE)
    @Getter @Setter
    private Date enddate;
    

    public PersonneFonction() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.fonction);
        hash = 79 * hash + Objects.hashCode(this.personne);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonneFonction other = (PersonneFonction) obj;
        if (!Objects.equals(this.fonction, other.fonction)) {
            return false;
        }
        return Objects.equals(this.personne, other.personne);
    }

  
    @Override
    public String toString() {
        return "PersonneFonction[ personne=" + this.getPersonne().getIdPERSONNE() + "fonction="+this.getFonction().getIdFONCTION()+"]";
    }
    
}
