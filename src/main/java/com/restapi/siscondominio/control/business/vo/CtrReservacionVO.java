package com.restapi.siscondominio.control.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.OffsetDateTime;


@Data
public class CtrReservacionVO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotNull(message = "lugId can not null")
    private Long lugId;

    @NotNull(message = "usuCedula can not null")
    private String usuCedula;
    @NotNull(message = "lugId can not null")
    private OffsetDateTime resFecha;

    @NotNull(message = "resHoraInicio can not null")
    private OffsetDateTime resHoraInicio;

    @NotNull(message = "resHoraFin can not null")
    private OffsetDateTime resHoraFin;


    @NotNull(message = "resActiva can not null")
    private Boolean resActiva;
}
