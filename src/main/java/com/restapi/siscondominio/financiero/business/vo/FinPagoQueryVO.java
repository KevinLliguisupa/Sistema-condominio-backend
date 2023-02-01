package com.restapi.siscondominio.financiero.business.vo;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Data
public class FinPagoQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long pagId;

    private Date pagFecha;

    private BigDecimal pagValor;

    private String cedTesorero;

}
