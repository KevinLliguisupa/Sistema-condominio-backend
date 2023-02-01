package com.restapi.siscondominio.financiero.business.vo;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Data
public class FinMontoQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long monId;

    private BigDecimal monValor;

    private Date monFechaAsignacion;

    private Date monFechaFin;

    private Long tdeId;

}
