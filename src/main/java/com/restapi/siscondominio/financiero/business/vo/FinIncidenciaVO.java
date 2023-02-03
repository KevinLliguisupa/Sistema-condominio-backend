package com.restapi.siscondominio.financiero.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;


@Data
public class FinIncidenciaVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "incId can not null")
    private Long incId;

    @NotNull(message = "usuCedula can not null")
    private String usuCedula;

    @NotNull(message = "incFechaReporte can not null")
    private Date incFechaReporte;

    @NotNull(message = "incDescripcion can not null")
    private String incDescripcion;

    @NotNull(message = "incEvidenciaIndicencia can not null")
    private String incEvidenciaIndicencia;

    @NotNull(message = "incSolucionada can not null")
    private Boolean incSolucionada;

    private Date incFechaSolucion;

    private String incEvidenciaSolucion;

}
