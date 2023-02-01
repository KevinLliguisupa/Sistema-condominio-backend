package com.restapi.siscondominio.control.business.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class CtrUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String usuCedula;

    private String usuApellidos;

    private String usuNombres;

    private String usuCorreo;

    private String usuTelefono;

    private String usuClave;

    private Boolean usuEstado;

}
