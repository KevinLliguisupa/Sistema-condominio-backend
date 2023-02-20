package com.restapi.siscondominio.control.persistence.entities;

import com.restapi.siscondominio.financiero.persistence.entities.FinDeuda;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ctr_usuario")
public class CtrUsuario implements Serializable {
    @Id
    @Column(name = "usu_cedula", nullable = false, length = 10)
    private String usuCedula;

    @Column(name = "usu_apellidos", nullable = false, length = 80)
    private String usuApellidos;

    @Column(name = "usu_nombres", nullable = false, length = 70)
    private String usuNombres;

    
    @Column(name = "usu_correo", nullable = false)
    private String usuCorreo;

    @Column(name = "usu_telefono", nullable = false, length = 10)
    private String usuTelefono;

    @Column(name = "usu_clave", nullable = false)
    private String usuClave;

    @Column(name = "usu_estado", nullable = false)
    private Boolean usuEstado = false;

    @OneToMany(mappedBy = "usuCedula")
    private Set<CtrReunion> ctrReunions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuCedula")
    private Set<CtrAsignacion> ctrAsignacions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuCedula")
    private Set<CtrReservacion> ctrReservacions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuCedula")
    private Set<CtrCasa> ctrCasas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuCedula")
    private Set<FinDeuda> finDeudas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuCedula")
    private Set<CtrAnuncio> ctrAnuncios = new LinkedHashSet<>();

}
