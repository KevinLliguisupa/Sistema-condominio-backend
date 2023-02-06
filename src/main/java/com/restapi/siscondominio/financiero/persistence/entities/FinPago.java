package com.restapi.siscondominio.financiero.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "fin_pago")
public class FinPago implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pag_id", nullable = false)
    private Long pagId;

    @Column(name = "pag_fecha", nullable = false)
    private LocalDate pagFecha;

    @Column(name = "pag_valor", nullable = false, precision = 10, scale = 3)
    private BigDecimal pagValor;

    
    @Column(name = "ced_tesorero", nullable = false)
    private String cedTesorero;

    @OneToMany(mappedBy = "pag")
    private List<FinDeudaPago> finDeudaPagos = new ArrayList<>();

}