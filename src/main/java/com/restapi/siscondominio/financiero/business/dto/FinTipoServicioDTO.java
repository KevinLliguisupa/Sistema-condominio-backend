package com.restapi.siscondominio.financiero.business.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class FinTipoServicioDTO implements Serializable {

    public FinTipoServicioDTO(Long tseId, String tseNombre, String tseDescripcion) {
        this.tseId = tseId;
        this.tseNombre = tseNombre;
        this.tseDescripcion = tseDescripcion;
    }
    public FinTipoServicioDTO() {
    }

    private static final long serialVersionUID = 1L;
    private Long tseId;

    private String tseNombre;

    private String tseDescripcion;

}
