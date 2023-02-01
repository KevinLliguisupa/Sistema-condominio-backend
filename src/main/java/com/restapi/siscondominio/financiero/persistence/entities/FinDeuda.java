package com.restapi.siscondominio.financiero.persistence.entities;

import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
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
@Table(name = "fin_deuda")
public class FinDeuda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deu_id", nullable = false)
    private Long deuId;

    @Column(name = "deu_fecha_corte", nullable = false)
    private LocalDate deuFechaCorte;

    @Column(name = "deu_saldo", nullable = false, precision = 10, scale = 3)
    private BigDecimal deuSaldo;

    @Column(name = "deu_cancelado", nullable = false)
    private Boolean deuCancelado = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mon_id", nullable = false)
    private FinMonto mon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usu_cedula", nullable = false)
    private CtrUsuario usuCedula;

    @OneToMany(mappedBy = "deu")
    private Set<FinDeudaPago> finDeudaPagos = new LinkedHashSet<>();

}