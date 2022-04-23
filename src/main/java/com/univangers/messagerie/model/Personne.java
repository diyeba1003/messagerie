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
@Table(name = "PERSONNE")
@NamedQueries({
    @NamedQuery(name = "Personne.findAll", query = "SELECT p FROM Personne p"),
    @NamedQuery(name = "Personne.findByIdPERSONNE", query = "SELECT p FROM Personne p WHERE p.idPERSONNE = :idPERSONNE"),
    @NamedQuery(name = "Personne.findByNom", query = "SELECT p FROM Personne p WHERE p.nom = :nom"),
    @NamedQuery(name = "Personne.findByPrenom", query = "SELECT p FROM Personne p WHERE p.prenom = :prenom")})
public class Personne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 80)
    @Getter
    @Setter
    private String idPERSONNE;

    @Column(length = 45)
    @Getter
    @Setter
    private String nom;

    @Column(length = 45)
    @Getter
    @Setter
    private String prenom;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "personne", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<PersonneFonction> personneFonctionList;

    @JoinColumn(name = "idPERSONNE", referencedColumnName = "idADRESSE", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Adresse adresse;

    public Personne() {
    }

    public Personne(String idPERSONNE) {
        this.idPERSONNE = idPERSONNE;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPERSONNE != null ? idPERSONNE.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        if ((this.idPERSONNE == null && other.idPERSONNE != null) || (this.idPERSONNE != null && !this.idPERSONNE.equals(other.idPERSONNE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.univangers.messagerie.model.Personne[ idPERSONNE=" + idPERSONNE + " ]";
    }

}
