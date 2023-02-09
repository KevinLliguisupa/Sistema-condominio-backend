package com.restapi.siscondominio.financiero.business.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class FinEgresoDineroServiciosDTO implements Serializable {
    public FinEgresoDineroServiciosDTO(String tipoServicio, BigDecimal valorPago, Date fechaPago, String cedulaTesorero, String nombreTesorero) {
        this.tipoServicio = tipoServicio;
        this.valorPago = valorPago;
        this.fechaPago = fechaPago;
        this.cedulaTesorero = cedulaTesorero;
        this.nombreTesorero = nombreTesorero;
    }

    public FinEgresoDineroServiciosDTO() {

    }


    private static final long serialVersionUID = 1L;
    private String tipoServicio;
    private BigDecimal valorPago;
    private Date fechaPago;
    private String cedulaTesorero;
    private String nombreTesorero;

}
