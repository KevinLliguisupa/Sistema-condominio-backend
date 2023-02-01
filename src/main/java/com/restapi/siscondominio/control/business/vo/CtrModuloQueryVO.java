package com.restapi.siscondominio.control.business.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class CtrModuloQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long modId;

    private String modNombre;

    private String modIcono;

}
