package com.restapi.siscondominio.financiero.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class FinMontoUpdateVO extends FinMontoVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
