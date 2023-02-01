package com.restapi.siscondominio.financiero.business.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class FinTipoDeudaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long tdeId;

    private String tdeNombre;

}
