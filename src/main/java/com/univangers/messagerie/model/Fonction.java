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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author etud
 */
@Entity
@Table(name = "FONCTION", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"title"})})
@NamedQueries({
    @NamedQuery(name = "Fonction.findAll", query = "SELECT f FROM Fonction f"),
    @NamedQuery(name = "Fonction.findByIdFONCTION", query = "SELECT f FROM Fonction f WHERE f.idFONCTION = :idFONCTION"),
    @NamedQuery(name = "Fonction.findByTitle", query = "SELECT f FROM Fonction f WHERE f.title = :title")})
public class Fonction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    @Getter
    @Setter
    private Integer idFONCTION;

    @Basic(optional = false)
    @Column(nullable = false, length = 120)
    @Getter
    @Setter
    private String title;

    @OneToMany(mappedBy = "fonction", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<PersonneFonction> personneFonctionList;

    public Fonction() {
    }

    public Fonction(Integer idFONCTION) {
        this.idFONCTION = idFONCTION;
    }

    public Fonction(Integer idFONCTION, String title) {
        this.idFONCTION = idFONCTION;
        this.title = title;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFONCTION != null ? idFONCTION.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fonction)) {
            return false;
        }
        Fonction other = (Fonction) object;
        if ((this.idFONCTION == null && other.idFONCTION != null) || (this.idFONCTION != null && !this.idFONCTION.equals(other.idFONCTION))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.univangers.messagerie.model.Fonction[ idFONCTION=" + idFONCTION + " ]";
    }

}
