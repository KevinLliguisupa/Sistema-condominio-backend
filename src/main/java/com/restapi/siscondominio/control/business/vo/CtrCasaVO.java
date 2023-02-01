package com.restapi.siscondominio.control.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class CtrCasaVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "casId can not null")
    private String casId;

    @NotNull(message = "usuCedula can not null")
    private String usuCedula;

    @NotNull(message = "casOcupado can not null")
    private Boolean casOcupado;

}
