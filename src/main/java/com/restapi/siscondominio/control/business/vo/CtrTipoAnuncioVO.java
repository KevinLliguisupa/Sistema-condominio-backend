package com.restapi.siscondominio.control.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class CtrTipoAnuncioVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "tanId can not null")
    private Long tanId;

    @NotNull(message = "tanNombre can not null")
    private String tanNombre;

}
