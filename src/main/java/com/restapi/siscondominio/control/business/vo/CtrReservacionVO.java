package com.restapi.siscondominio.control.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;


@Data
public class CtrReservacionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "resId can not null")
    private Long resId;

    @NotNull(message = "lugId can not null")
    private Long lugId;

    @NotNull(message = "usuCedula can not null")
    private String usuCedula;

    private Date resFecha;

    @NotNull(message = "resHoraInicio can not null")
    private Date resHoraInicio;

    @NotNull(message = "resHoraFin can not null")
    private Date resHoraFin;

    @NotNull(message = "resAprobado can not null")
    private Boolean resAprobado;

}
