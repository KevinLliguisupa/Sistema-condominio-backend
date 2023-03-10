package com.restapi.siscondominio.control.business.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class CtrLugarVO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotNull(message = "lugNombre can not null")
    private String lugNombre;



}
