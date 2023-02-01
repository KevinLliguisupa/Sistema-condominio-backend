package com.restapi.siscondominio.seguridad.business.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class SegBitacoraQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long bitId;

    private String bitCedGuardia;

    private String bitFecha;

    private String bitEvento;

    private String bitCedIngreso;

    private String bitDescripcion;

    private Boolean bitIngreso;

}
