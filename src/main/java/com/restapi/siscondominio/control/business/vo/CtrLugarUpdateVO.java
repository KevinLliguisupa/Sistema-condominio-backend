package com.restapi.siscondominio.control.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CtrLugarUpdateVO extends CtrLugarVO implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotNull(message = "lugId can not null")
    private Long lugId;

    @NotNull(message = "lugNombre can not null")
    private String lugNombre;




}
