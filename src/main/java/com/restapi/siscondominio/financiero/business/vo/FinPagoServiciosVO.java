package com.restapi.siscondominio.financiero.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class FinPagoServiciosVO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotNull(message = "tseId can not null")
    private Long tseId;

    @NotNull(message = "pseMonto can not null")
    private BigDecimal pseMonto;

    @NotNull(message = "pseRecibo can not null")
    private String pseRecibo;

    @NotNull(message = "pseCedTesorero can not null")
    private String pseCedTesorero;


}
