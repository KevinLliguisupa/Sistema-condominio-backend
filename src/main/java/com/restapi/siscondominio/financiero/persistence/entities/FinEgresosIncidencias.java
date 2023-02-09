package com.restapi.siscondominio.financiero.persistence.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * $table.getTableComment()
 */
@Data
@Entity
@Table(name = "fin_egresos_incidencias")
public class FinEgresosIncidencias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "anio")
    private BigDecimal anio;

    @Column(name = "mes")
    private BigDecimal mes;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "servicio")
    private String servicio;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "incidencias")
    private Long incidencias;

    @Id
    @Column(name = "concat")
    private String concat;

}
