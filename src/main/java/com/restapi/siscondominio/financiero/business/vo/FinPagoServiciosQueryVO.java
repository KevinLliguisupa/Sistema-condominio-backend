package com.restapi.siscondominio.financiero.business.vo;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Data
public class FinPagoServiciosQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long pseId;

    private Long tseId;

    private BigDecimal pseMonto;

    private String pseRecibo;

    private Date pseFechaPago;

    private String pseCedTesorero;

}
