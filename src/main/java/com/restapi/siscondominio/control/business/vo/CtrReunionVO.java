package com.restapi.siscondominio.control.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;


@Data
public class CtrReunionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "usuCedula can not null")
    private String usuCedula;

    @NotNull(message = "reuFecha can not null")
    private LocalDate reuFecha;

    @NotNull(message = "lugId can not null")
    private Long lugId;

}
