package com.restapi.siscondominio.financiero.business.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDate;

@Data
public class FinIngresosDineroDTO implements Serializable {

    public FinIngresosDineroDTO() {
    }

    public FinIngresosDineroDTO(Long id, LocalDate fecha, BigDecimal valor, String cedulaTesorero, String nombreTesorero, String cedulaCondomino, String nombreCondomino) {
        this.id = id;
        this.fecha = fecha;
        this.valor = valor;
        this.cedulaTesorero = cedulaTesorero;
        this.nombreTesorero = nombreTesorero;
        this.cedulaCondomino = cedulaCondomino;
        this.nombreCondomino = nombreCondomino;
    }

    private static final long serialVersionUID = 1L;
    private Long id;
    private LocalDate fecha;
    private BigDecimal valor;
    private String cedulaTesorero;
    private String nombreTesorero;
    private String cedulaCondomino;
    private String nombreCondomino;
}
