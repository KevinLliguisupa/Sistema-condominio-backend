package com.restapi.siscondominio.seguridad.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class SegBitacoraUpdateVO extends SegBitacoraVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "bitId can not null")
    private Long bitId;

    @NotNull(message = "bitEvento can not null")
    private String bitEvento;

    @NotNull(message = "bitCedIngreso can not null")
    private String bitCedIngreso;

    private String bitDescripcion;



}
