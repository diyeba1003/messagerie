/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "personne_physique", catalog = "messagerie", schema = "")
@NamedQueries({
    @NamedQuery(name = "PersonnePhysique.findAll", query = "SELECT p FROM PersonnePhysique p"),
    @NamedQuery(name = "PersonnePhysique.findByIdAdresse", query = "SELECT p FROM PersonnePhysique p WHERE p.idAdresse = :idAdresse"),
    @NamedQuery(name = "PersonnePhysique.findByNom", query = "SELECT p FROM PersonnePhysique p WHERE p.nom = :nom"),
    @NamedQuery(name = "PersonnePhysique.findByPrenom", query = "SELECT p FROM PersonnePhysique p WHERE p.prenom = :prenom")})
public class PersonnePhysique extends Adresse  {

    @Column(name = "nom", length = 60)
    @Getter
    @Setter
    private String nom;
    
    @Column(name = "prenom", length = 60)
    @Getter
    @Setter
    private String prenom;
    /*
    @JoinColumn(name = "id_adresse", referencedColumnName = "id_adresse", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Adresse adresse;
*/
    public PersonnePhysique() {
    }

    @Override
    public String toString() {
        return "PersonnePhysique{" + "nom=" + nom + ", prenom=" + prenom + ", adresse="  + '}';
    }
  
    
}
