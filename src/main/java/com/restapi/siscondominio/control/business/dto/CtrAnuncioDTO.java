package com.restapi.siscondominio.control.business.dto;


import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class CtrAnuncioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long ancId;

    private String usuCedula;

    private String ancTitulo;

    private String ancDescripcion;

    private Date ancFechaPublicacion;

    private String ancPrioridad;

    private Boolean ancEstado;

    private Long tanId;

}
