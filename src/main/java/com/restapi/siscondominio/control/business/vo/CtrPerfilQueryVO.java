package com.restapi.siscondominio.control.business.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class CtrPerfilQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long prfId;

    private String prfNombre;

    private String prfRutaAcceso;

    private Long modId;

}
