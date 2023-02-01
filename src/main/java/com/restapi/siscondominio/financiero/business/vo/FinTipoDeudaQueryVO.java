package com.restapi.siscondominio.financiero.business.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class FinTipoDeudaQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long tdeId;

    private String tdeNombre;

}
