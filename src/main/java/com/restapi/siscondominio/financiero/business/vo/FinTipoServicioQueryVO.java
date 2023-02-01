package com.restapi.siscondominio.financiero.business.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class FinTipoServicioQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long tseId;

    private String tseNombre;

    private String tseDescripcion;

}
