package com.restapi.siscondominio.financiero.business.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FinPagoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long pagId;

    private LocalDate pagFecha;

    private BigDecimal pagValor;

    private String cedTesorero;

}
