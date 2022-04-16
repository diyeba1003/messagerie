/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author etud
 */
@Entity
@Table(name = "liste", catalog = "messagerie", schema = "")
@NamedQueries({
    @NamedQuery(name = "Liste.findAll", query = "SELECT l FROM Liste l"),
    @NamedQuery(name = "Liste.findByIdAdresse", query = "SELECT l FROM Liste l WHERE l.idAdresse = :idAdresse")})
public class Liste extends Adresse {
/*
    @JoinColumn(name = "id_adresse", referencedColumnName = "id_adresse", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Adresse adresse;
*/
    public Liste() {
        super();
    }

   
    public String toString() {
        return this.toString();
    }
    
}
