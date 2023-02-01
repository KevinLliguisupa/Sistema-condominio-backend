package com.restapi.siscondominio.control.business.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class CtrAsignacionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long asgId;

    private Long prfId;

    private String usuCedula;

}
