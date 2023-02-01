package com.restapi.siscondominio.control.business.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class CtrPerfilDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long prfId;

    private String prfNombre;

    private String prfRutaAcceso;

    private Long modId;

}
