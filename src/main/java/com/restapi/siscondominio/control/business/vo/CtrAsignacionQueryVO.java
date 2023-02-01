package com.restapi.siscondominio.control.business.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class CtrAsignacionQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long asgId;

    private Long prfId;

    private String usuCedula;

}
