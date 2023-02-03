package com.restapi.siscondominio.financiero.persistence.entities;

import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "fin_incidencia")
public class FinIncidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inc_id", nullable = false)
    private Long incId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usu_cedula", nullable = false)
    private CtrUsuario usuCedula;

    @NotNull
    @Column(name = "inc_fecha_reporte", nullable = false)
    private LocalDate incFechaReporte;

    @NotNull
    @Column(name = "inc_descripcion", nullable = false)
    private String incDescripcion;

    @NotNull
    @Column(name = "inc_evidencia_indicencia", nullable = false)
    private String incEvidenciaIndicencia;

    @NotNull
    @Column(name = "inc_solucionada", nullable = false)
    private Boolean incSolucionada = false;

    @Column(name = "inc_fecha_solucion")
    private LocalDate incFechaSolucion;

    @Column(name = "inc_evidencia_solucion")
    private String incEvidenciaSolucion;

    @OneToMany(mappedBy = "inc")
    private Set<FinGasto> finGastos = new LinkedHashSet<>();

}