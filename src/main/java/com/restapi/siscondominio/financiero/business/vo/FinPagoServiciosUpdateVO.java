package com.restapi.siscondominio.financiero.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class FinPagoServiciosUpdateVO  implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "pseRecibo can not null")
    private String pseRecibo;


}
