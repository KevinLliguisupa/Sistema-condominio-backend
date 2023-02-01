package com.restapi.siscondominio.financiero.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class FinDeudaPagoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "depId can not null")
    private Long depId;

    @NotNull(message = "deuId can not null")
    private Long deuId;

    @NotNull(message = "pagId can not null")
    private Long pagId;

}
