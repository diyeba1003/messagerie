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
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author etud
 */
@Entity
@Table(name="RATTACHEMENT")
@NamedQueries({
    @NamedQuery(name = "Rattachement.findAll", query = "SELECT r FROM Rattachement r"),
    @NamedQuery(name = "Rattachement.findByIdRATTACHEMENT", query = "SELECT r FROM Rattachement r WHERE r.idRATTACHEMENT = :idRATTACHEMENT"),
    @NamedQuery(name = "Rattachement.findByTitle", query = "SELECT r FROM Rattachement r WHERE r.title = :title")})
public class Rattachement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    @Getter
    @Setter
    private Integer idRATTACHEMENT;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 120)
    @Getter
    @Setter
    private String title;

    public Rattachement() {
    }

    public Rattachement(Integer idRATTACHEMENT) {
        this.idRATTACHEMENT = idRATTACHEMENT;
    }

    public Rattachement(Integer idRATTACHEMENT, String title) {
        this.idRATTACHEMENT = idRATTACHEMENT;
        this.title = title;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRATTACHEMENT != null ? idRATTACHEMENT.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rattachement)) {
            return false;
        }
        Rattachement other = (Rattachement) object;
        if ((this.idRATTACHEMENT == null && other.idRATTACHEMENT != null) || (this.idRATTACHEMENT != null && !this.idRATTACHEMENT.equals(other.idRATTACHEMENT))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.univangers.messagerie.model.Rattachement[ idRATTACHEMENT=" + idRATTACHEMENT + " ]";
    }
    
}
