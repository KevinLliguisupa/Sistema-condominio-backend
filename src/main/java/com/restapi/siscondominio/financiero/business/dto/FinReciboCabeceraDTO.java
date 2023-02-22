package com.restapi.siscondominio.financiero.business.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Data
public class FinReciboCabeceraDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long pagId;

    private String usuCedula;

    private String usuApellidos;

    private String usuNombres;

    private String usuCorreo;

    private Date pagFecha;

    private BigDecimal pagValor;

    private String cedTesorero;

}
