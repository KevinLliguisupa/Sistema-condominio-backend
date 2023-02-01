package com.restapi.siscondominio.control.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "ctr_reunion")
public class CtrReunion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reu_id", nullable = false)
    private Long reuId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usu_cedula", nullable = false)
    private CtrUsuario usuCedula;

    @Column(name = "reu_fecha", nullable = false)
    private LocalDate reuFecha;

    @Column(name = "reu_estado", nullable = false)
    private Boolean reuEstado = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lug_id", nullable = false)
    private CtrLugar lug;

}