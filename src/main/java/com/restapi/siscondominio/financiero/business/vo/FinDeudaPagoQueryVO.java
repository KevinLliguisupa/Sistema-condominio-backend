package com.restapi.siscondominio.financiero.business.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class FinDeudaPagoQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long depId;

    private Long deuId;

    private Long pagId;

}
