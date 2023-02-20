package com.restapi.siscondominio.financiero.business.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinDeudaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long deuId;

    private String usuCedula;

    private LocalDate deuFechaCorte;

    private BigDecimal deuSaldo;

    private Boolean deuCancelado;

    private FinMontoDTO monto;


}
