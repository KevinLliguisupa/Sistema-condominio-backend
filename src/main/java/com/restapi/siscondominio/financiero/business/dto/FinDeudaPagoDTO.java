package com.restapi.siscondominio.financiero.business.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class FinDeudaPagoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
//    private Long depId;
//
//    private Long deuId;

    private BigDecimal depValorPagado;

    private FinPagoDTO pago;


}
