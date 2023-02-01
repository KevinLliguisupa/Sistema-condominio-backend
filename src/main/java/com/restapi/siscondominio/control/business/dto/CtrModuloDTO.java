package com.restapi.siscondominio.control.business.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class CtrModuloDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long modId;

    private String modNombre;

    private String modIcono;

}
