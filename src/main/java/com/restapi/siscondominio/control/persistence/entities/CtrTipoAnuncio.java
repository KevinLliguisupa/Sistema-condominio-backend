package com.restapi.siscondominio.control.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ctr_tipo_anuncio")
public class CtrTipoAnuncio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tan_id", nullable = false)
    private Long tanId;

    @Column(name = "tan_nombre", nullable = false, length = 60)
    private String tanNombre;

    @OneToMany(mappedBy = "tan")
    private Set<CtrAnuncio> ctrAnuncios = new LinkedHashSet<>();

}