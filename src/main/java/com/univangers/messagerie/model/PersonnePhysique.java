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

/**
 *
 * @author etud
 */
@Entity
@Table(name = "personne_physique", catalog = "messagerie", schema = "")
@NamedQueries({
    @NamedQuery(name = "PersonnePhysique.findAll", query = "SELECT p FROM PersonnePhysique p"),
    @NamedQuery(name = "PersonnePhysique.findByIdAdresse", query = "SELECT p FROM PersonnePhysique p WHERE p.idAdresse = :idAdresse"),
    @NamedQuery(name = "PersonnePhysique.findByNom", query = "SELECT p FROM PersonnePhysique p WHERE p.nom = :nom"),
    @NamedQuery(name = "PersonnePhysique.findByPrenom", query = "SELECT p FROM PersonnePhysique p WHERE p.prenom = :prenom")})
public class PersonnePhysique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_adresse", nullable = false, length = 80)
    private String idAdresse;
    @Column(name = "nom", length = 60)
    private String nom;
    @Column(name = "prenom", length = 60)
    private String prenom;
    @JoinColumn(name = "id_adresse", referencedColumnName = "id_adresse", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Adresse adresse;

    public PersonnePhysique() {
    }

    public PersonnePhysique(String idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(String idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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
        hash += (idAdresse != null ? idAdresse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonnePhysique)) {
            return false;
        }
        PersonnePhysique other = (PersonnePhysique) object;
        if ((this.idAdresse == null && other.idAdresse != null) || (this.idAdresse != null && !this.idAdresse.equals(other.idAdresse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.univangers.messagerie.model.PersonnePhysique[ idAdresse=" + idAdresse + " ]";
    }
    
}
