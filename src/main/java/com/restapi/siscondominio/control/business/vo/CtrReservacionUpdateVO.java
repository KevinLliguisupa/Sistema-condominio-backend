package com.restapi.siscondominio.control.business.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class CtrReservacionUpdateVO extends CtrReservacionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "resId can not null")
    private Long resId;
    @NotNull(message = "lugId can not null")
    private Long lugId;
    @NotNull(message = "lugId can not null")
    private OffsetDateTime resFecha;

    @NotNull(message = "resHoraInicio can not null")
    private OffsetDateTime resHoraInicio;

    @NotNull(message = "resHoraFin can not null")
    private OffsetDateTime resHoraFin;

}
