/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dto;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author etud
 */
public class MessageDto {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private String object;

    @Getter
    @Setter
    private String body;

    @Getter
    @Setter
    private AdresseDto expediteurDto;

    @Getter
    @Setter
    private List<AdresseDto> destinataires;

    public MessageDto() {
    }

    public MessageDto(Integer id, Date date, String object, String body, AdresseDto expediteurDto, List<AdresseDto> destinataires) {
        this.id = id;
        this.date = date;
        this.object = object;
        this.body = body;
        this.expediteurDto = expediteurDto;
        this.destinataires = destinataires;
    }

}
