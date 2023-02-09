package com.restapi.siscondominio.seguridad.business.dto;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class SegBitacoraDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long bitId;

    private String bitCedGuardia;

    private LocalDate bitFecha;

    private String bitEvento;

    private String bitCedIngreso;

    private String bitDescripcion;

    private Boolean bitIngreso;

}
