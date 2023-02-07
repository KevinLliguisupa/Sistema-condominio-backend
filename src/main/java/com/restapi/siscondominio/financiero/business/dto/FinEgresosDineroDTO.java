package com.restapi.siscondominio.financiero.business.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class FinEgresosDineroDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    public FinEgresosDineroDTO(String cedulaTesorero, String nombreTesorero, BigDecimal valorPago, Date fechaPago, String descripcionIncidencia, String tipoServicio) {
        this.cedulaTesorero = cedulaTesorero;
        this.nombreTesorero = nombreTesorero;
        this.valorPago = valorPago;
        this.fechaPago = fechaPago;
        this.descripcionIncidencia = descripcionIncidencia;
        this.tipoServicio = tipoServicio;
    }

    public FinEgresosDineroDTO() {

    }

    private String cedulaTesorero;
    private String nombreTesorero;
    private BigDecimal valorPago;
    private Date fechaPago;
    private String descripcionIncidencia;
    private String tipoServicio;

}
