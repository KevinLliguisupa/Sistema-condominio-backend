package com.restapi.siscondominio.control.business.vo;


import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class CtrReunionQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long reuId;

    private String usuCedula;

    private Date reuFecha;

    private Boolean reuEstado;

    private Long lugId;

}
