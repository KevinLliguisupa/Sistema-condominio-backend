package com.restapi.siscondominio.control.business.dto;


import com.restapi.siscondominio.control.business.vo.CtrAnuncioVO;
import com.restapi.siscondominio.control.persistence.entities.CtrAnuncio;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Data
public class CtrAnuncioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long ancId;

    private String usuCedula;

    private String ancTitulo;

    private String ancDescripcion;

    private LocalDate ancFechaPublicacion;

    private String ancPrioridad;

    private Boolean ancEstado;

    private Long tanId;

    public CtrAnuncioDTO() {
    }




}
