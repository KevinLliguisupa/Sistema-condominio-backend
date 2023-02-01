package com.restapi.siscondominio.control.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class CtrUsuarioVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "usuCedula can not null")
    private String usuCedula;

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

    @NotNull(message = "usuEstado can not null")
    private Boolean usuEstado;

}
