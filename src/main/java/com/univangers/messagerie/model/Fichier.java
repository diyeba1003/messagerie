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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author etud
 */
@Entity
@Table(catalog = "messagerie", schema = "")
@NamedQueries({
    @NamedQuery(name = "Fichier.findAll", query = "SELECT f FROM Fichier f"),
    @NamedQuery(name = "Fichier.findByIdFICHIER", query = "SELECT f FROM Fichier f WHERE f.idFICHIER = :idFICHIER"),
    @NamedQuery(name = "Fichier.findByFiletype", query = "SELECT f FROM Fichier f WHERE f.filetype = :filetype"),
    @NamedQuery(name = "Fichier.findByFilename", query = "SELECT f FROM Fichier f WHERE f.filename = :filename"),
    @NamedQuery(name = "Fichier.findByFilepath", query = "SELECT f FROM Fichier f WHERE f.filepath = :filepath")})
public class Fichier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idFICHIER;
    @Column(length = 8)
    private String filetype;
    @Column(length = 120)
    private String filename;
    @Basic(optional = false)
    @Column(nullable = false, length = 300)
    private String filepath;
    @JoinColumn(name = "messageID", referencedColumnName = "idMESSAGE", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Message messageID;

    public Fichier() {
    }

    public Fichier(Integer idFICHIER) {
        this.idFICHIER = idFICHIER;
    }

    public Fichier(Integer idFICHIER, String filepath) {
        this.idFICHIER = idFICHIER;
        this.filepath = filepath;
    }

    public Integer getIdFICHIER() {
        return idFICHIER;
    }

    public void setIdFICHIER(Integer idFICHIER) {
        this.idFICHIER = idFICHIER;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Message getMessageID() {
        return messageID;
    }

    public void setMessageID(Message messageID) {
        this.messageID = messageID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFICHIER != null ? idFICHIER.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fichier)) {
            return false;
        }
        Fichier other = (Fichier) object;
        if ((this.idFICHIER == null && other.idFICHIER != null) || (this.idFICHIER != null && !this.idFICHIER.equals(other.idFICHIER))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.univangers.messagerie.model.Fichier[ idFICHIER=" + idFICHIER + " ]";
    }
    
}
