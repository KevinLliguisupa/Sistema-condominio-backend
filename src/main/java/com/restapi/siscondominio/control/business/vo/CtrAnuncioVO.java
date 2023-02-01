package com.restapi.siscondominio.control.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;


@Data
public class CtrAnuncioVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "ancId can not null")
    private Long ancId;

    @NotNull(message = "usuCedula can not null")
    private String usuCedula;

    @NotNull(message = "ancTitulo can not null")
    private String ancTitulo;

    @NotNull(message = "ancDescripcion can not null")
    private String ancDescripcion;

    @NotNull(message = "ancFechaPublicacion can not null")
    private Date ancFechaPublicacion;

    @NotNull(message = "ancPrioridad can not null")
    private String ancPrioridad;

    @NotNull(message = "ancEstado can not null")
    private Boolean ancEstado;

    @NotNull(message = "tanId can not null")
    private Long tanId;

}
