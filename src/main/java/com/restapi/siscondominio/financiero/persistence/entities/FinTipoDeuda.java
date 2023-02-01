package com.restapi.siscondominio.financiero.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "fin_tipo_deuda")
public class FinTipoDeuda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tde_id", nullable = false)
    private Long tdeId;

    
    @Column(name = "tde_nombre", nullable = false)
    private String tdeNombre;

    @OneToMany(mappedBy = "tde")
    private Set<FinMonto> finMontos = new LinkedHashSet<>();

}