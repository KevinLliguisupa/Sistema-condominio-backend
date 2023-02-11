package com.restapi.siscondominio.financiero.business.vo;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class FinPagoComunVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "usuCedula can not null")
    private Long deuId;

    @NotNull(message = "cedTesorero can not null")
    private String cedTesorero;

}
