package com.restapi.siscondominio.financiero.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;


@Data
public class FinIncidenciaVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "usuCedula can not null")
    private String usuCedula;

    @NotNull(message = "incDescripcion can not null")
    private String incDescripcion;

    @NotNull(message = "incEvidenciaIndicencia can not null")
    private String incEvidenciaIndicencia;


}
