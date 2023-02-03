package com.restapi.siscondominio.financiero.business.dto;


import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class FinIncidenciaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long incId;

    private String usuCedula;

    private Date incFechaReporte;

    private String incDescripcion;

    private String incEvidenciaIndicencia;

    private Boolean incSolucionada;

    private Date incFechaSolucion;

    private String incEvidenciaSolucion;

}
