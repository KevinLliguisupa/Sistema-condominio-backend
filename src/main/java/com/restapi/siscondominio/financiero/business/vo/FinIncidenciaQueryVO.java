package com.restapi.siscondominio.financiero.business.vo;


import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class FinIncidenciaQueryVO implements Serializable {
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
