package com.restapi.siscondominio.control.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CtrPerfilUpdateVO extends CtrPerfilVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "prfId can not null")
    private Long prfId;

    @NotNull(message = "prfNombre can not null")
    private String prfNombre;

    @NotNull(message = "prfRutaAcceso can not null")
    private String prfRutaAcceso;



}
