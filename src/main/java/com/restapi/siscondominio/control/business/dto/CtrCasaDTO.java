package com.restapi.siscondominio.control.business.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class CtrCasaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String casId;

    private String usuCedula;

    private Boolean casOcupado;

}
