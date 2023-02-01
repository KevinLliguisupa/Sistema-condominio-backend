package com.restapi.siscondominio.control.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ctr_lugar")
public class CtrLugar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lug_id", nullable = false)
    private Long lugId;

    @Column(name = "lug_nombre", nullable = false, length = 60)
    private String lugNombre;

    @Column(name = "lug_disponible", nullable = false)
    private Boolean lugDisponible = false;

    @OneToMany(mappedBy = "lug")
    private Set<CtrReunion> ctrReunions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "lug")
    private Set<CtrReservacion> ctrReservacions = new LinkedHashSet<>();

}