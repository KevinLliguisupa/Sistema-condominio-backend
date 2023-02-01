package com.restapi.siscondominio.seguridad.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class SegBitacoraVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "bitId can not null")
    private Long bitId;

    @NotNull(message = "bitCedGuardia can not null")
    private String bitCedGuardia;

    @NotNull(message = "bitFecha can not null")
    private String bitFecha;

    @NotNull(message = "bitEvento can not null")
    private String bitEvento;

    @NotNull(message = "bitCedIngreso can not null")
    private String bitCedIngreso;

    private String bitDescripcion;

    @NotNull(message = "bitIngreso can not null")
    private Boolean bitIngreso;

}
