package com.restapi.siscondominio.financiero.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;


@Data
public class FinMontoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "monId can not null")
    private Long monId;

    @NotNull(message = "monValor can not null")
    private BigDecimal monValor;

    @NotNull(message = "monFechaAsignacion can not null")
    private Date monFechaAsignacion;

    private Date monFechaFin;

    @NotNull(message = "tdeId can not null")
    private Long tdeId;

}
