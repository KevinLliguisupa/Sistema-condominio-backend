package com.restapi.siscondominio.control.business.dto;


import com.restapi.siscondominio.control.persistence.entities.CtrTipoAnuncio;
import lombok.Data;

import java.io.Serializable;

@Data
public class CtrTipoAnuncioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long tanId;

    private String tanNombre;




}
