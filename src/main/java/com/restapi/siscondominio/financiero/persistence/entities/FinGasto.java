package com.restapi.siscondominio.financiero.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "fin_gastos")
public class FinGasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gas_id", nullable = false)
    private Long gasId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tse_id", nullable = false)
    private FinTipoServicio tse;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inc_id", nullable = false)
    private FinIncidencia inc;

    @NotNull
    @Column(name = "gas_ced_tesorero", nullable = false)
    private String gasCedTesorero;

    @NotNull
    @Column(name = "gas_pago", nullable = false, precision = 10, scale = 3)
    private BigDecimal gasPago;

    @NotNull
    @Column(name = "gas_fecha", nullable = false)
    private LocalDate gasFecha;

    @NotNull
    @Column(name = "gas_recibo", nullable = false)
    private String gasRecibo;

}