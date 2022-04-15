/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author etud
 */
@Entity
@Table(name = "fonction", catalog = "messagerie", schema = "")
@NamedQueries({
    @NamedQuery(name = "Fonction.findAll", query = "SELECT f FROM Fonction f"),
    @NamedQuery(name = "Fonction.findByIdFonction", query = "SELECT f FROM Fonction f WHERE f.idFonction = :idFonction"),
    @NamedQuery(name = "Fonction.findByTitre", query = "SELECT f FROM Fonction f WHERE f.titre = :titre")})
public class Fonction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fonction", nullable = false)
    private Integer idFonction;
    @Column(name = "titre", length = 75)
    private String titre;

    public Fonction() {
    }

    public Fonction(Integer idFonction) {
        this.idFonction = idFonction;
    }

    public Integer getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(Integer idFonction) {
        this.idFonction = idFonction;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFonction != null ? idFonction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fonction)) {
            return false;
        }
        Fonction other = (Fonction) object;
        if ((this.idFonction == null && other.idFonction != null) || (this.idFonction != null && !this.idFonction.equals(other.idFonction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.univangers.messagerie.model.Fonction[ idFonction=" + idFonction + " ]";
    }
    
}
