package com.restapi.siscondominio.control.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class CtrPerfilVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "prfId can not null")
    private Long prfId;

    @NotNull(message = "prfNombre can not null")
    private String prfNombre;

    @NotNull(message = "prfRutaAcceso can not null")
    private String prfRutaAcceso;

    @NotNull(message = "modId can not null")
    private Long modId;

}
