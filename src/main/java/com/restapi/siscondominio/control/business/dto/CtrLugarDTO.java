package com.restapi.siscondominio.control.business.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class CtrLugarDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long lugId;

    private String lugNombre;

    private Boolean lugDisponible;

}
