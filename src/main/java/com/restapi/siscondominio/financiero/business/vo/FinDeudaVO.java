package com.restapi.siscondominio.financiero.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;


@Data
public class FinDeudaVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "deuId can not null")
    private Long deuId;

    @NotNull(message = "deuFechaCorte can not null")
    private Date deuFechaCorte;

    @NotNull(message = "deuSaldo can not null")
    private BigDecimal deuSaldo;

    @NotNull(message = "deuCancelado can not null")
    private Boolean deuCancelado;

    @NotNull(message = "monId can not null")
    private Long monId;

    @NotNull(message = "usuCedula can not null")
    private String usuCedula;

}
