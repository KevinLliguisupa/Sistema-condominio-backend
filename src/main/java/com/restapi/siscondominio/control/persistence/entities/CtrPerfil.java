package com.restapi.siscondominio.control.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ctr_perfil")
public class CtrPerfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prf_id", nullable = false)
    private Long prfId;

    @NotNull
    @Column(name = "prf_nombre", nullable = false)
    private String prfNombre;

    @Size(max = 100)
    @NotNull
    @Column(name = "prf_ruta_acceso", nullable = false, length = 100)
    private String prfRutaAcceso;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mod_id", nullable = false)
    private CtrModulo mod;

    @OneToMany(mappedBy = "prf")
    private Set<CtrAsignacion> ctrAsignacions = new LinkedHashSet<>();

}