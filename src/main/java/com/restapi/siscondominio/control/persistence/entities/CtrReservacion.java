package com.restapi.siscondominio.control.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ctr_reservacion")
public class CtrReservacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id", nullable = false)
    private Long resId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lug_id", nullable = false)
    private CtrLugar lug;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usu_cedula", nullable = false)
    private CtrUsuario usuCedula;

    @Column(name = "res_fecha")
    private LocalDate resFecha;

    @NotNull
    @Column(name = "res_hora_inicio", nullable = false)
    private LocalTime resHoraInicio;

    @NotNull
    @Column(name = "res_hora_fin", nullable = false)
    private LocalTime resHoraFin;

    @NotNull
    @Column(name = "res_aprobado", nullable = false)
    private Boolean resAprobado = false;

    @NotNull
    @Column(name = "res_activa", nullable = false)
    private Boolean resActiva = false;

}