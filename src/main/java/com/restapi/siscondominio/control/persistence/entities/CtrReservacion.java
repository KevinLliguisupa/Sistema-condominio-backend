package com.restapi.siscondominio.control.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "ctr_reservacion")
public class CtrReservacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id", nullable = false)
    private Long resId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lug_id", nullable = false)
    private CtrLugar lug;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usu_cedula", nullable = false)
    private CtrUsuario usuCedula;

    @Column(name = "res_fecha")
    private LocalDate resFecha;

    @Column(name = "res_hora_inicio", nullable = false)
    private LocalDate resHoraInicio;

    @Column(name = "res_hora_fin", nullable = false)
    private LocalDate resHoraFin;

    @Column(name = "res_aprobado", nullable = false)
    private Boolean resAprobado = false;

}