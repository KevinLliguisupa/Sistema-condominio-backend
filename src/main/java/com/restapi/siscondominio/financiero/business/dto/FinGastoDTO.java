package com.restapi.siscondominio.financiero.business.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class FinGastoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    public FinGastoDTO(Long gasId, String gasCedTesorero,String gasNomTesorero,BigDecimal gasPago, Date gasFecha, String gasRecibo, Long tseId, Long incId) {
        this.gasId = gasId;
        this.gasCedTesorero = gasCedTesorero;
        this.gasNomTesorero=gasNomTesorero;
        this.gasPago = gasPago;
        this.gasFecha = gasFecha;
        this.gasRecibo = gasRecibo;
        this.tseId = tseId;
        this.incId = incId;
    }

    public FinGastoDTO() {
    }
    private Long gasId;

    private String gasCedTesorero;
    private String gasNomTesorero;

    private BigDecimal gasPago;

    private Date gasFecha;

    private String gasRecibo;

    private Long tseId;
    private Long incId;

}
