package com.restapi.siscondominio.control.business.dto;


import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.time.OffsetDateTime;

@Data
public class CtrReservacionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long resId;

    private Long lugId;

    private String usuCedula;

    private Date resFecha;

    private OffsetDateTime resHoraInicio;

    private OffsetDateTime resHoraFin;

    private Boolean resAprobado;
    private Boolean resActiva;

}
