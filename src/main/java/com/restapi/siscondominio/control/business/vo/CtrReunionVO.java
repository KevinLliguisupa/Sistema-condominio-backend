package com.restapi.siscondominio.control.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;


@Data
public class CtrReunionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "reuId can not null")
    private Long reuId;

    @NotNull(message = "usuCedula can not null")
    private String usuCedula;

    @NotNull(message = "reuFecha can not null")
    private Date reuFecha;

    @NotNull(message = "reuEstado can not null")
    private Boolean reuEstado;

    @NotNull(message = "lugId can not null")
    private Long lugId;

}
