package com.restapi.siscondominio.financiero.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class FinIncidenciaSolucionarVO implements Serializable{
    private static final long serialVersionUID = 1L;

    private String incEvidenciaSolucion;

}
