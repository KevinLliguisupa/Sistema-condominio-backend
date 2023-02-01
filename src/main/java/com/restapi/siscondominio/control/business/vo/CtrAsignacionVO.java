package com.restapi.siscondominio.control.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class CtrAsignacionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "asgId can not null")
    private Long asgId;

    @NotNull(message = "prfId can not null")
    private Long prfId;

    @NotNull(message = "usuCedula can not null")
    private String usuCedula;

}
