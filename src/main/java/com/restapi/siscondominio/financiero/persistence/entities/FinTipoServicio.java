package com.restapi.siscondominio.financiero.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "fin_tipo_servicio")
public class FinTipoServicio implements Serializable {
    @Id
    @Column(name = "tse_id", nullable = false)
    private Long tseId;

    
    @Column(name = "tse_nombre", nullable = false)
    private String tseNombre;

    
    @Column(name = "tse_descripcion", nullable = false)
    private String tseDescripcion;

    @OneToMany(mappedBy = "tse")
    private Set<FinGasto> finGastos = new LinkedHashSet<>();

}