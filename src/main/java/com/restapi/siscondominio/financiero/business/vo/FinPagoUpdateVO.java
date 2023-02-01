package com.restapi.siscondominio.financiero.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class FinPagoUpdateVO extends FinPagoVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
