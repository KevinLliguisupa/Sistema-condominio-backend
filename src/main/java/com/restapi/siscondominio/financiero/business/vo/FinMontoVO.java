package com.restapi.siscondominio.financiero.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;


@Data
public class FinMontoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "monValor can not null")
    private BigDecimal monValor;

    @NotNull(message = "tdeId can not null")
    private Long tdeId;

}
