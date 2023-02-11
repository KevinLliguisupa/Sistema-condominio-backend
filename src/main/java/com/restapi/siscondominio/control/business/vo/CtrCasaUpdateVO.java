package com.restapi.siscondominio.control.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CtrCasaUpdateVO extends CtrCasaVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "casId can not null")
    private String casId;

    @NotNull(message = "usuCedula can not null")
    private String usuCedula;
}
