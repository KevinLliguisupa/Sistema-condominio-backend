package com.restapi.siscondominio.control.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class CtrReunionUpdateVO extends CtrReunionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "reuId can not null")
    private Long reuId;

    @NotNull(message = "reuFecha can not null")
    private LocalDate reuFecha;

    @NotNull(message = "lugId can not null")
    private Long lugId;

}
