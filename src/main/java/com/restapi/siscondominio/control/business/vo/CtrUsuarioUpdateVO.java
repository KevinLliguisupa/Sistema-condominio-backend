package com.restapi.siscondominio.control.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CtrUsuarioUpdateVO extends CtrUsuarioVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "usuApellidos can not null")
    private String usuApellidos;

    @NotNull(message = "usuNombres can not null")
    private String usuNombres;

    @NotNull(message = "usuCorreo can not null")
    private String usuCorreo;

    @NotNull(message = "usuTelefono can not null")
    private String usuTelefono;

    @NotNull(message = "usuClave can not null")
    private String usuClave;

}
