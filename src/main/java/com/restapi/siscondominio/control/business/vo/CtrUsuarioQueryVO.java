package com.restapi.siscondominio.control.business.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class CtrUsuarioQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String usuCedula;

    private String usuApellidos;

    private String usuNombres;

    private String usuCorreo;

    private String usuTelefono;

    private String usuClave;

    private Boolean usuEstado;

}
