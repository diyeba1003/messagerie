/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.services;

import com.univangers.messagerie.dao.RattachementDaoInterface;
import com.univangers.messagerie.dto.RattachementDto;
import com.univangers.messagerie.model.Rattachement;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author etud
 */
@Service
@Transactional
public class RattachementService implements RattachementServiceInterface {

    @Autowired
    private RattachementDaoInterface rattachementDao;

    private Rattachement convertToEntity(RattachementDto rattachementDto) {
        Rattachement rattachement = new Rattachement();
        rattachement.setIdRATTACHEMENT(rattachementDto.getId());
        rattachement.setTitle(rattachementDto.getTitle());
        return rattachement;
    }

    @Override
    public void insertRattachementDto(RattachementDto rattachementDto) {
        Rattachement rattachement = convertToEntity(rattachementDto);
        rattachementDao.insertRattachement(rattachement);
    }

    @Override
    public RattachementDto findRattachementDtoById(Integer id) {
        Rattachement ra = rattachementDao.findRattachementById(id);
        return convertToDto(ra);
    }

    private RattachementDto convertToDto(Rattachement rattachement) {

        RattachementDto rattachementDto = new RattachementDto();
        rattachementDto.setId(rattachement.getIdRATTACHEMENT());
        rattachementDto.setTitle(rattachement.getTitle());
        return rattachementDto;
    }

}
