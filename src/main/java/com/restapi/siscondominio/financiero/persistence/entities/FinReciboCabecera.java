package com.restapi.siscondominio.financiero.persistence.entities;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * $table.getTableComment()
 */
@Data
@Entity
@Immutable
@Table(name = "fin_recibo_cabecera")
public class FinReciboCabecera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "pag_id")
    private Long pagId;

    @Column(name = "usu_cedula")
    private String usuCedula;

    @Column(name = "usu_apellidos")
    private String usuApellidos;

    @Column(name = "usu_nombres")
    private String usuNombres;

    @Column(name = "usu_correo")
    private String usuCorreo;

    @Column(name = "pag_fecha")
    private Date pagFecha;

    @Column(name = "pag_valor")
    private BigDecimal pagValor;

    @Column(name = "ced_tesorero")
    private String cedTesorero;

}
