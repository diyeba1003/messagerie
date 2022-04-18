/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dto;

import java.util.ArrayList;
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
    private List<AdresseDto> destinataireDtoList;

    @Getter
    @Setter
    private List<AdresseDto> destinataireCopieDtoList;

    @Getter
    @Setter
    private List<AdresseDto> transfertDtoList;

    @Getter
    @Setter
    private List<FichierDto> fichierDtoList;

    public MessageDto() {
        this.destinataireDtoList = new ArrayList<>();
        this.destinataireCopieDtoList = new ArrayList<>();
        this.transfertDtoList = new ArrayList<>();
        this.fichierDtoList = new ArrayList<>();
    }

}
