package com.restapi.siscondominio.control.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ctr_modulo")
public class CtrModulo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mod_id", nullable = false)
    private Long modId;

    @Column(name = "mod_nombre", nullable = false, length = 50)
    private String modNombre;

    @Column(name = "mod_icono", length = 50)
    private String modIcono;

    @OneToMany(mappedBy = "mod")
    private Set<CtrPerfil> ctrPerfils = new LinkedHashSet<>();

}