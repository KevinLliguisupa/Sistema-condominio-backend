package com.restapi.siscondominio.seguridad.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;


@Data
public class SegBitacoraVO implements Serializable {
    private static final long serialVersionUID = 1L;



    @NotNull(message = "bitCedGuardia can not null")
    private String bitCedGuardia;


    @NotNull(message = "bitEvento can not null")
    private String bitEvento;

    @NotNull(message = "bitCedIngreso can not null")
    private String bitCedIngreso;

    private String bitDescripcion;



}
