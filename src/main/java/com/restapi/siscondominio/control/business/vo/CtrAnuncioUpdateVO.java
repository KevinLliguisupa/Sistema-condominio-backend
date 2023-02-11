package com.restapi.siscondominio.control.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CtrAnuncioUpdateVO extends CtrAnuncioVO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotNull(message = "ancId can not null")
    private Long ancId;


    @NotNull(message = "ancTitulo can not null")
    private String ancTitulo;

    @NotNull(message = "ancDescripcion can not null")
    private String ancDescripcion;

    @NotNull(message = "ancPrioridad can not null")
    private String ancPrioridad;

    @NotNull(message = "tanId can not null")
    private Long tanId;


}
