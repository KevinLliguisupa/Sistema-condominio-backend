package com.restapi.siscondominio.financiero.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "fin_tipo_servicio")
public class FinTipoServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tse_id", nullable = false)
    private Long tseId;

    @NotNull
    @Column(name = "tse_nombre", nullable = false)
    private String tseNombre;

    @NotNull
    @Column(name = "tse_descripcion", nullable = false)
    private String tseDescripcion;
    @NotNull
    @Column(name = "tse_incidencia", nullable = false)
    private Boolean tseIncidencia;

    @OneToMany(mappedBy = "tseId")
    private Set<FinGasto> finGastos = new LinkedHashSet<>();
    @OneToMany(mappedBy = "tseId")
    private Set<FinPagoServicios> finPagoServicios = new LinkedHashSet<>();


}