package com.restapi.siscondominio.financiero.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "fin_gastos")
public class FinGasto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gas_id", nullable = false)
    private Long gasId;

    
    @Column(name = "gas_ced_tesorero", nullable = false)
    private String gasCedTesorero;

    @Column(name = "gas_pago", nullable = false, precision = 10, scale = 3)
    private BigDecimal gasPago;

    @Column(name = "gas_fecha", nullable = false)
    private LocalDate gasFecha;

    
    @Column(name = "gas_recibo", nullable = false)
    private String gasRecibo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tse_id", nullable = false)
    private FinTipoServicio tse;

}