package com.restapi.siscondominio.control.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class CtrModuloVO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotNull(message = "modNombre can not null")
    private String modNombre;

    private String modIcono;

}
