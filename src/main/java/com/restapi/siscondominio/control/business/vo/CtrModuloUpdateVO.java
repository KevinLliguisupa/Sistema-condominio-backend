package com.restapi.siscondominio.control.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CtrModuloUpdateVO extends CtrModuloVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "modId can not null")
    private Long modId;

    @NotNull(message = "modNombre can not null")
    private String modNombre;

    private String modIcono;
}
