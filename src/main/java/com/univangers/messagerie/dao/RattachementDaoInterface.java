/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.dao;

import com.univangers.messagerie.model.Rattachement;
import java.util.List;

/**
 *
 * @author etud
 */
public interface RattachementDaoInterface {

    public void insertRattachement(Rattachement rattachement);

    public Rattachement findRattachementById(Integer idRattachement);

    public List<Rattachement> findAllRattachement();
}
