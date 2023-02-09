package com.restapi.siscondominio.financiero.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * $table.getTableComment()
 */
@Data
@Entity
@Table(name = "fin_pago_servicios")
public class FinPagoServicios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pse_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pseId;

    @Column(name = "tse_id", nullable = false)
    private Long tseId;

    @Column(name = "pse_monto", nullable = false)
    private BigDecimal pseMonto;

    @Column(name = "pse_recibo", nullable = false)
    private String pseRecibo;

    @Column(name = "pse_fecha_pago", nullable = false)
    private Date pseFechaPago;

    @Column(name = "pse_ced_tesorero", nullable = false)
    private String pseCedTesorero;

}
