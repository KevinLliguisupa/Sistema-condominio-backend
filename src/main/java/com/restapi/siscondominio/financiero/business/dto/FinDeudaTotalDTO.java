package com.restapi.siscondominio.financiero.business.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class FinDeudaTotalDTO implements Serializable {
    public FinDeudaTotalDTO(BigDecimal valor) {
        this.valor = valor;
    }

    public FinDeudaTotalDTO() {

    }
    private static final long serialVersionUID = 1L;
    private BigDecimal valor;
}
