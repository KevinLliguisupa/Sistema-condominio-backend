package com.restapi.siscondominio.financiero.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroPagosDTO {
    private static final long serialVersionUID = 1L;
    private Long deuId;

    private LocalDate deuFechaCorte;

    private String tdeNombre;

    private BigDecimal recValorAdeudado;

    private BigDecimal depValorPagado;

    private BigDecimal recSaldo;
}
