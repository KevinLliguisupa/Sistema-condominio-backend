package com.restapi.siscondominio.financiero.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class FinTipoServicioUpdateVO extends FinTipoServicioVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
