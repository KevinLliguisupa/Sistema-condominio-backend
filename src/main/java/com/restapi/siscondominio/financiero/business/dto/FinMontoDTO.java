package com.restapi.siscondominio.financiero.business.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Data
public class FinMontoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long monId;

    private BigDecimal monValor;

    private LocalDate monFechaAsignacion;

    private LocalDate monFechaFin;

    private FinTipoDeudaDTO tipoDeuda;

}
