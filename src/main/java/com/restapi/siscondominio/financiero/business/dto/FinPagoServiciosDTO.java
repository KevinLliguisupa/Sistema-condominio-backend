package com.restapi.siscondominio.financiero.business.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class FinPagoServiciosDTO implements Serializable {

    public FinPagoServiciosDTO(Long pseId, Long tseId, BigDecimal pseMonto, String pseRecibo, Date pseFechaPago, String pseCedTesorero, String pseNomTesorero) {
        this.pseId = pseId;
        this.tseId = tseId;
        this.pseMonto = pseMonto;
        this.pseRecibo = pseRecibo;
        this.pseFechaPago = pseFechaPago;
        this.pseCedTesorero = pseCedTesorero;
        this.pseNomTesorero = pseNomTesorero;
    }
    public FinPagoServiciosDTO() {

    }

    private static final long serialVersionUID = 1L;
    private Long pseId;

    private Long tseId;

    private BigDecimal pseMonto;

    private String pseRecibo;

    private Date pseFechaPago;

    private String pseCedTesorero;

    private String pseNomTesorero;

}
