package com.restapi.siscondominio.financiero.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class FinGastoUpdateVO  implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "gasRecibo can not null")
    private String gasRecibo;

}
