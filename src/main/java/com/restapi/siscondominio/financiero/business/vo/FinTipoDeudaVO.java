package com.restapi.siscondominio.financiero.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class FinTipoDeudaVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "tdeId can not null")
    private Long tdeId;

    @NotNull(message = "tdeNombre can not null")
    private String tdeNombre;

}
