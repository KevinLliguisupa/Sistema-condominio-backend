package com.restapi.siscondominio.financiero.business.vo;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Data
public class FinGastoQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long gasId;

    private String gasCedTesorero;

    private BigDecimal gasPago;

    private Date gasFecha;

    private String gasRecibo;

    private Long tseId;

}
