package com.restapi.siscondominio.control.business.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class CtrCasaQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String casId;

    private String usuCedula;

    private Boolean casOcupado;

}
