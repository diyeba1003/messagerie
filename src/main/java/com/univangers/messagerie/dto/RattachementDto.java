/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.univangers.messagerie.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author etud
 */
public class RattachementDto {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String title;

    public RattachementDto() {
    }

    public RattachementDto(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

}
