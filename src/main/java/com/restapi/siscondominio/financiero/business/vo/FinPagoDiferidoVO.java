package com.restapi.siscondominio.financiero.business.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinPagoDiferidoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "usuCedula can not null")
    private String usuCedula;

    @NotNull(message = "pagValor can not null")
    private BigDecimal pagValor;

    @NotNull(message = "cedTesorero can not null")
    private String cedTesorero;

}
