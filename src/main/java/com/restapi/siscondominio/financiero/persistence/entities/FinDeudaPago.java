package com.restapi.siscondominio.financiero.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
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

}