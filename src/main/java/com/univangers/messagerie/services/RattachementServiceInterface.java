/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dto.RattachementDto;

/**
 *
 * @author etud
 */
public interface RattachementServiceInterface {

    public void insertRattachementDto(RattachementDto rattachementDto);

    public RattachementDto findRattachementDtoById(Integer id);
}
