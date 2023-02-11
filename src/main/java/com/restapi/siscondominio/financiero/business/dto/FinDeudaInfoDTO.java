package com.restapi.siscondominio.financiero.business.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class FinDeudaInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long deuId;

    private String usuCedula;

    private LocalDate deuFechaCorte;

    private BigDecimal deuSaldo;

    private Boolean deuCancelado;

    private FinMontoDTO monto;

    List<FinDeudaPagoDTO> pagos;

}
