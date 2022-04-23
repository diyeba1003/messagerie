/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Fichier;
import java.util.List;

/**
 *
 * @author etud
 */
public interface FichierDaoInterface {

    public void insertFichier(Fichier fichier);

    public Fichier findFichierById(Integer idFichier);

    public List<Fichier> findAllFichier();
}
