package com.restapi.siscondominio.financiero.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fin_deuda_pago")
public class FinDeudaPago implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dep_id", nullable = false)
    private Long depId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deu_id", nullable = false)
    private FinDeuda deu;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pag_id", nullable = false)
    private FinPago pag;

    @Column(name = "dep_valor_pagado", precision = 10, scale = 3)
    private BigDecimal depValorPagado;
}