package com.restapi.siscondominio.financiero.business.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class FinEgresosServiciosDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    public FinEgresosServiciosDTO(BigDecimal anio, BigDecimal mes, Long codigo, String servicio, BigDecimal valor) {
        this.anio = anio;
        this.mes = mes;
        this.codigo = codigo;
        this.servicio = servicio;
        this.valor = valor;
    }

    public FinEgresosServiciosDTO() {

    }

    private BigDecimal anio;


    private BigDecimal mes;


    private Long codigo;


    private String servicio;


    private BigDecimal valor;

}
