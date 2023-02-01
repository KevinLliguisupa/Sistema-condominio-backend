package com.restapi.siscondominio.seguridad.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "seg_bitacora")
public class SegBitacora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bit_id", nullable = false)
    private Long bitId;

    @NotNull
    @Column(name = "bit_ced_guardia", nullable = false)
    private String bitCedGuardia;

    @NotNull
    @Column(name = "bit_fecha", nullable = false)
    private String bitFecha;

    @NotNull
    @Column(name = "bit_evento", nullable = false)
    private String bitEvento;

    @NotNull
    @Column(name = "bit_ced_ingreso", nullable = false)
    private String bitCedIngreso;

    @Column(name = "bit_descripcion")
    private String bitDescripcion;

    @NotNull
    @Column(name = "bit_ingreso", nullable = false)
    private Boolean bitIngreso = false;

}