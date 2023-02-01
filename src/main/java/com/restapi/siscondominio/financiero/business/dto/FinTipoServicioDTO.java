package com.restapi.siscondominio.financiero.business.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class FinTipoServicioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long tseId;

    private String tseNombre;

    private String tseDescripcion;

}