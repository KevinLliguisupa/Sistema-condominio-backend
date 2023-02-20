package com.restapi.siscondominio.financiero.business.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FinIncidenciaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    public FinIncidenciaDTO(Long incId, String usuCedula, String usuNombre,Date incFechaReporte, String incDescripcion, String incEvidenciaIndicencia, Boolean incSolucionada, Date incFechaSolucion, String incEvidenciaSolucion) {
        this.incId = incId;
        this.usuCedula = usuCedula;
        this.incFechaReporte = incFechaReporte;
        this.incDescripcion = incDescripcion;
        this.incEvidenciaIndicencia = incEvidenciaIndicencia;
        this.incSolucionada = incSolucionada;
        this.incFechaSolucion = incFechaSolucion;
        this.incEvidenciaSolucion = incEvidenciaSolucion;
        this.usuNombre=usuNombre;
    }
    public FinIncidenciaDTO() {

    }

    private Long incId;

    private String usuCedula;
    private  String usuNombre;

    private Date incFechaReporte;

    private String incDescripcion;

    private String incEvidenciaIndicencia;

    private Boolean incSolucionada;

    private Date incFechaSolucion;

    private String incEvidenciaSolucion;


}
