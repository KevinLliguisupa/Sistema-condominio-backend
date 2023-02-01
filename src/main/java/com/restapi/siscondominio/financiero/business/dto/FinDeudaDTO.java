package com.restapi.siscondominio.financiero.business.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Data
public class FinDeudaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long deuId;

    private Date deuFechaCorte;

    private BigDecimal deuSaldo;

    private Boolean deuCancelado;

    private Long monId;

    private String usuCedula;

}
