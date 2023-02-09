package com.restapi.siscondominio.financiero.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "fin_ingresos_gastos_mensuales")
public class FinIngresosGastosMensuale {
    @Column(name = "anio")
    private BigDecimal anio;

    @Column(name = "mes")
    private BigDecimal mes;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "tipo")
    @Type(type = "org.hibernate.type.TextType")
    private String tipo;

    @Id
    @Column(name = "fecha")
    @Type(type = "org.hibernate.type.TextType")
    private String fecha;

}