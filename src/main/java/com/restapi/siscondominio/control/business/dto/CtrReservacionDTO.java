package com.restapi.siscondominio.control.business.dto;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Data
public class CtrReservacionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long resId;

    private Long lugId;

    private String usuCedula;

    private LocalDate resFecha;

    private LocalTime resHoraInicio;

    private LocalTime resHoraFin;

    private Boolean resAprobado;

    private Boolean resActiva;


}
