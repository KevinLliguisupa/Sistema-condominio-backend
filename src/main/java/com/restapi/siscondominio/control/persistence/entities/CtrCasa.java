package com.restapi.siscondominio.control.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ctr_casa")
public class CtrCasa implements Serializable {
    @Id
    @Column(name = "cas_id", nullable = false, length = 6)
    private String casId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usu_cedula", nullable = false)
    private CtrUsuario usuCedula;

    @Column(name = "cas_ocupado", nullable = false)
    private Boolean casOcupado = false;

}