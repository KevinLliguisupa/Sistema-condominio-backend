package com.restapi.siscondominio.control.business.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class CtrTipoAnuncioQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long tanId;

    private String tanNombre;

}
