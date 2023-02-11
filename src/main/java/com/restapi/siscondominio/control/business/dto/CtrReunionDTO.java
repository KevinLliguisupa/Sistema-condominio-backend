package com.restapi.siscondominio.control.business.dto;


import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Data
public class CtrReunionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long reuId;

    private String usuCedula;

    private LocalDate reuFecha;

    private Boolean reuEstado;

    private Long lugId;

}
