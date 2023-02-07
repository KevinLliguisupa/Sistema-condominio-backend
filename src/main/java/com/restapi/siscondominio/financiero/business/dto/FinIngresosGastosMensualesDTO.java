package com.restapi.siscondominio.financiero.business.dto;

import lombok.Data;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class FinIngresosGastosMensualesDTO implements Serializable {

    public FinIngresosGastosMensualesDTO(BigDecimal anio, BigDecimal mes, BigDecimal valor) {
        this.anio = anio;
        this.mes = mes;
        this.valor = valor;
    }

    public FinIngresosGastosMensualesDTO() {

    }

    private static final long serialVersionUID = 1L;

    private BigDecimal anio;

    private BigDecimal mes;

    private BigDecimal valor;

}

