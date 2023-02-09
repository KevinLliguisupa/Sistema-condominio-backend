package com.restapi.siscondominio.financiero.business.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinEgresosIncidenciasDTO {

    private static final long serialVersionUID = 1L;

    public FinEgresosIncidenciasDTO(BigDecimal anio, BigDecimal mes, Long codigo, String servicio, BigDecimal valor, Long incidencias) {
        this.anio = anio;
        this.mes = mes;
        this.codigo = codigo;
        this.servicio = servicio;
        this.valor = valor;
        this.incidencias = incidencias;
    }

    public FinEgresosIncidenciasDTO() {

    }

    private BigDecimal anio;


    private BigDecimal mes;


    private Long codigo;


    private String servicio;


    private BigDecimal valor;


    private Long incidencias;


}
