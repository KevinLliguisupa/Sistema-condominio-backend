package com.restapi.siscondominio.control.business.vo;


import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class CtrReservacionQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long resId;

    private Long lugId;

    private String usuCedula;

    private Date resFecha;

    private Date resHoraInicio;

    private Date resHoraFin;

    private Boolean resAprobado;

}
