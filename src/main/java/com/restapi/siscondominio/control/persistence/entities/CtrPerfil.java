package com.restapi.siscondominio.control.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ctr_perfil")
public class CtrPerfil implements Serializable {
    @Id
    @Column(name = "prf_id", nullable = false)
    private Long prfId;

    
    @Column(name = "prf_nombre", nullable = false)
    private String prfNombre;

    @Column(name = "prf_ruta_acceso", nullable = false, length = 100)
    private String prfRutaAcceso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mod_id", nullable = false)
    private CtrModulo mod;

    @OneToMany(mappedBy = "prf")
    private Set<CtrAsignacion> ctrAsignacions = new LinkedHashSet<>();

}