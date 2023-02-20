package com.restapi.siscondominio.financiero.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinReciboDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private String usuCedula;

    private String usuApellidos;

    private String usuNombres;

    private String usuCorreo;

    private Long pagId;

    private LocalDate pagFecha;

    private BigDecimal pagValor;

    private String cedTesorero;

    private List<RegistroPagosDTO> detalles;

}
