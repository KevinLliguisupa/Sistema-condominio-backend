package com.restapi.siscondominio.financiero.persistence.entities;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Mapping for DB view
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Immutable
@Table(name = "fin_recibo")
public class FinRecibo {
    @Id
    @Column(name = "dep_id")
    private Long depId;

    @Size(max = 10)
    @Column(name = "usu_cedula", length = 10)
    private String usuCedula;

    @Size(max = 80)
    @Column(name = "usu_apellidos", length = 80)
    private String usuApellidos;

    @Size(max = 70)
    @Column(name = "usu_nombres", length = 70)
    private String usuNombres;

    @Column(name = "usu_correo")
    private String usuCorreo;

    @Column(name = "pag_id")
    private Long pagId;

    @Column(name = "pag_fecha")
    private LocalDate pagFecha;

    @Column(name = "pag_valor", precision = 10, scale = 3)
    private BigDecimal pagValor;

    @Column(name = "ced_tesorero")
    private String cedTesorero;

    @Column(name = "deu_id")
    private Long deuId;

    @Column(name = "deu_fecha_corte")
    private LocalDate deuFechaCorte;

    @Column(name = "tde_nombre")
    private String tdeNombre;

    @Column(name = "rec_valor_adeudado")
    private BigDecimal recValorAdeudado;

    @Column(name = "dep_valor_pagado", precision = 10, scale = 3)
    private BigDecimal depValorPagado;

    @Column(name = "rec_saldo")
    private BigDecimal recSaldo;

}