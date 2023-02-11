package com.restapi.siscondominio.control.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CtrAsignacionUpdateVO extends CtrAsignacionVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
