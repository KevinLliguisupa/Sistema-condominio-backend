package com.restapi.siscondominio.financiero.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;


@Data
public class FinPagoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "pagId can not null")
    private Long pagId;

    @NotNull(message = "pagFecha can not null")
    private Date pagFecha;

    @NotNull(message = "pagValor can not null")
    private BigDecimal pagValor;

    @NotNull(message = "cedTesorero can not null")
    private String cedTesorero;

}
