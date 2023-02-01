package com.restapi.siscondominio.financiero.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class FinTipoServicioVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "tseId can not null")
    private Long tseId;

    @NotNull(message = "tseNombre can not null")
    private String tseNombre;

    @NotNull(message = "tseDescripcion can not null")
    private String tseDescripcion;

}
