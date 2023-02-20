package com.restapi.siscondominio.financiero.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "fin_monto")
public class FinMonto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mon_id", nullable = false)
    private Long monId;

    @Column(name = "mon_valor", nullable = false, precision = 10, scale = 3)
    private BigDecimal monValor;

    @Column(name = "mon_fecha_asignacion", nullable = false)
    private LocalDate monFechaAsignacion;

    @Column(name = "mon_fecha_fin")
    private LocalDate monFechaFin;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tde_id", nullable = false)
    private FinTipoDeuda tipoDeuda;

    @OneToMany(mappedBy = "monto")
    private Set<FinDeuda> finDeudas = new LinkedHashSet<>();

}