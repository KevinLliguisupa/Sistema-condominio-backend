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

    //@NotNull(message = "gasId can not null")
    //private Long gasId;

    //@NotNull(message = "tseId can not null")
    //private Long tseId;
    //@NotNull(message = "incId can not null")
    //private Long incId;
    //@NotNull(message = "gasCedTesorero can not null")
    //private String gasCedTesorero;

    @NotNull(message = "gasPago can not null")
    private BigDecimal gasPago;

    //@NotNull(message = "gasFecha can not null")
    //private Date gasFecha;

    @NotNull(message = "gasRecibo can not null")
    private String gasRecibo;

}
