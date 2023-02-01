package com.restapi.siscondominio.financiero.business.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class FinDeudaPagoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long depId;

    private Long deuId;

    private Long pagId;

}
