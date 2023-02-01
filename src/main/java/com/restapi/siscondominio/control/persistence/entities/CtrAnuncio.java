package com.restapi.siscondominio.control.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "ctr_anuncio")
public class CtrAnuncio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anc_id", nullable = false)
    private Long ancId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usu_cedula", nullable = false)
    private CtrUsuario usuCedula;

    @Column(name = "anc_titulo", nullable = false, length = 50)
    private String ancTitulo;

    @Column(name = "anc_descripcion", nullable = false, length = 200)
    private String ancDescripcion;

    @Column(name = "anc_fecha_publicacion", nullable = false)
    private LocalDate ancFechaPublicacion;

    @Column(name = "anc_prioridad", nullable = false, length = 10)
    private String ancPrioridad;

    @Column(name = "anc_estado", nullable = false)
    private Boolean ancEstado = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tan_id", nullable = false)
    private CtrTipoAnuncio tan;

}