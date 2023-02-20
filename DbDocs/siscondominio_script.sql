
CREATE SEQUENCE public.seg_bitacora_bit_id_seq;

CREATE TABLE public.seg_bitacora (
                                     bit_id INTEGER NOT NULL DEFAULT nextval('public.seg_bitacora_bit_id_seq'),
                                     bit_ced_guardia VARCHAR NOT NULL,
                                     bit_fecha date NOT NULL,
                                     bit_evento VARCHAR NOT NULL,
                                     bit_ced_ingreso VARCHAR NOT NULL,
                                     bit_descripcion VARCHAR,
                                     bit_ingreso BOOLEAN NOT NULL,
                                     CONSTRAINT seg_bitacora_pk PRIMARY KEY (bit_id)
);


ALTER SEQUENCE public.seg_bitacora_bit_id_seq OWNED BY public.seg_bitacora.bit_id;

CREATE SEQUENCE public.fin_tipo_servicio_tis_id_seq;

CREATE TABLE public.fin_tipo_servicio (
                                          tse_id INTEGER NOT NULL DEFAULT nextval('public.fin_tipo_servicio_tis_id_seq'),
                                          tse_incidencia BOOLEAN NOT NULL,
                                          tse_nombre VARCHAR NOT NULL,
                                          tse_descripcion VARCHAR NOT NULL,
                                          CONSTRAINT fin_tipo_servicio_pk PRIMARY KEY (tse_id)
);


ALTER SEQUENCE public.fin_tipo_servicio_tis_id_seq OWNED BY public.fin_tipo_servicio.tse_id;

CREATE SEQUENCE public.fin_pago_servicios_pse_id_seq;

CREATE TABLE public.fin_pago_servicios (
                                           pse_id INTEGER NOT NULL DEFAULT nextval('public.fin_pago_servicios_pse_id_seq'),
                                           tse_id INTEGER NOT NULL,
                                           pse_monto NUMERIC(10,2) NOT NULL,
                                           pse_recibo VARCHAR NOT NULL,
                                           pse_fecha_pago DATE NOT NULL,
                                           pse_ced_tesorero VARCHAR(10) NOT NULL,
                                           CONSTRAINT fin_pago_servicios_pk PRIMARY KEY (pse_id)
);


ALTER SEQUENCE public.fin_pago_servicios_pse_id_seq OWNED BY public.fin_pago_servicios.pse_id;

CREATE SEQUENCE public.fin_pago_abo_id_seq;

CREATE TABLE public.fin_pago (
                                 pag_id INTEGER NOT NULL DEFAULT nextval('public.fin_pago_abo_id_seq'),
                                 pag_fecha DATE NOT NULL,
                                 pag_valor NUMERIC(10,3) NOT NULL,
                                 ced_tesorero VARCHAR NOT NULL,
                                 CONSTRAINT fin_pago_pk PRIMARY KEY (pag_id)
);


ALTER SEQUENCE public.fin_pago_abo_id_seq OWNED BY public.fin_pago.pag_id;

CREATE SEQUENCE public.fin_tipo_pago_pag_id_seq;

CREATE TABLE public.fin_tipo_deuda (
                                       tde_id INTEGER NOT NULL DEFAULT nextval('public.fin_tipo_pago_pag_id_seq'),
                                       tde_nombre VARCHAR NOT NULL,
                                       CONSTRAINT fin_tipo_deuda_pk PRIMARY KEY (tde_id)
);


ALTER SEQUENCE public.fin_tipo_pago_pag_id_seq OWNED BY public.fin_tipo_deuda.tde_id;

CREATE SEQUENCE public.fin_monto_val_id_seq;

CREATE TABLE public.fin_monto (
                                  mon_id INTEGER NOT NULL DEFAULT nextval('public.fin_monto_val_id_seq'),
                                  mon_valor NUMERIC(10,3) NOT NULL,
                                  mon_fecha_asignacion DATE NOT NULL,
                                  mon_fecha_fin DATE,
                                  tde_id INTEGER NOT NULL,
                                  CONSTRAINT fin_monto_pk PRIMARY KEY (mon_id)
);


ALTER SEQUENCE public.fin_monto_val_id_seq OWNED BY public.fin_monto.mon_id;

CREATE SEQUENCE public.ctr_tipo_anuncio_tip_id_seq;

CREATE TABLE public.ctr_tipo_anuncio (
                                         tan_id INTEGER NOT NULL DEFAULT nextval('public.ctr_tipo_anuncio_tip_id_seq'),
                                         tan_nombre VARCHAR(60) NOT NULL,
                                         CONSTRAINT ctr_tipo_anuncio_pk PRIMARY KEY (tan_id)
);


ALTER SEQUENCE public.ctr_tipo_anuncio_tip_id_seq OWNED BY public.ctr_tipo_anuncio.tan_id;

CREATE SEQUENCE public.ctr_lugar_lug_id_seq_1;

CREATE TABLE public.ctr_lugar (
                                  lug_id INTEGER NOT NULL DEFAULT nextval('public.ctr_lugar_lug_id_seq_1'),
                                  lug_nombre VARCHAR(60) NOT NULL,
                                  lug_disponible BOOLEAN NOT NULL,
                                  CONSTRAINT ctr_lugar_pk PRIMARY KEY (lug_id)
);


ALTER SEQUENCE public.ctr_lugar_lug_id_seq_1 OWNED BY public.ctr_lugar.lug_id;

CREATE SEQUENCE public.ctr_modulo_mod_id_seq;

CREATE TABLE public.ctr_modulo (
                                   mod_id INTEGER NOT NULL DEFAULT nextval('public.ctr_modulo_mod_id_seq'),
                                   mod_nombre VARCHAR(50) NOT NULL,
                                   mod_icono VARCHAR(50),
                                   CONSTRAINT ctr_modulo_pk PRIMARY KEY (mod_id)
);


ALTER SEQUENCE public.ctr_modulo_mod_id_seq OWNED BY public.ctr_modulo.mod_id;

CREATE SEQUENCE public.ctr_perfil_prf_id_seq_1;

CREATE TABLE public.ctr_perfil (
                                   prf_id INTEGER NOT NULL DEFAULT nextval('public.ctr_perfil_prf_id_seq_1'),
                                   prf_nombre VARCHAR NOT NULL,
                                   prf_ruta_acceso VARCHAR(100) NOT NULL,
                                   mod_id INTEGER NOT NULL,
                                   CONSTRAINT ctr_perfil_pk PRIMARY KEY (prf_id)
);


ALTER SEQUENCE public.ctr_perfil_prf_id_seq_1 OWNED BY public.ctr_perfil.prf_id;

CREATE TABLE public.ctr_usuario (
                                    usu_cedula VARCHAR(10) NOT NULL,
                                    usu_apellidos VARCHAR(80) NOT NULL,
                                    usu_nombres VARCHAR(70) NOT NULL,
                                    usu_correo VARCHAR NOT NULL,
                                    usu_telefono VARCHAR(10) NOT NULL,
                                    usu_clave VARCHAR NOT NULL,
                                    usu_estado BOOLEAN NOT NULL,
                                    CONSTRAINT ctr_usuario_pk PRIMARY KEY (usu_cedula)
);


CREATE UNIQUE INDEX ctr_usuario_idx
    ON public.ctr_usuario
        ( usu_correo );

CREATE SEQUENCE public.fin_incidencia_inc_id_seq;

CREATE TABLE public.fin_incidencia (
                                       inc_id INTEGER NOT NULL DEFAULT nextval('public.fin_incidencia_inc_id_seq'),
                                       usu_cedula VARCHAR(10) NOT NULL,
                                       inc_fecha_reporte DATE NOT NULL,
                                       inc_descripcion VARCHAR NOT NULL,
                                       inc_evidencia_indicencia VARCHAR NOT NULL,
                                       inc_solucionada BOOLEAN NOT NULL,
                                       inc_fecha_solucion DATE,
                                       inc_evidencia_solucion VARCHAR,
                                       CONSTRAINT fin_incidencia_pk PRIMARY KEY (inc_id)
);


ALTER SEQUENCE public.fin_incidencia_inc_id_seq OWNED BY public.fin_incidencia.inc_id;

CREATE SEQUENCE public.fin_gastos_gas_id_seq;

CREATE TABLE public.fin_gastos (
                                   gas_id INTEGER NOT NULL DEFAULT nextval('public.fin_gastos_gas_id_seq'),
                                   tse_id INTEGER NOT NULL,
                                   inc_id INTEGER NOT NULL,
                                   gas_ced_tesorero VARCHAR NOT NULL,
                                   gas_pago NUMERIC(10,3) NOT NULL,
                                   gas_fecha DATE NOT NULL,
                                   gas_recibo VARCHAR NOT NULL,
                                   CONSTRAINT fin_gastos_pk PRIMARY KEY (gas_id)
);


ALTER SEQUENCE public.fin_gastos_gas_id_seq OWNED BY public.fin_gastos.gas_id;

CREATE SEQUENCE public.fin_pago_ali_id_seq;

CREATE TABLE public.fin_deuda (
                                  deu_id INTEGER NOT NULL DEFAULT nextval('public.fin_pago_ali_id_seq'),
                                  deu_fecha_corte DATE NOT NULL,
                                  deu_saldo NUMERIC(10,3) NOT NULL,
                                  deu_cancelado BOOLEAN NOT NULL,
                                  mon_id INTEGER NOT NULL,
                                  usu_cedula VARCHAR(10) NOT NULL,
                                  CONSTRAINT fin_deuda_pk PRIMARY KEY (deu_id)
);


ALTER SEQUENCE public.fin_pago_ali_id_seq OWNED BY public.fin_deuda.deu_id;

CREATE SEQUENCE public.fin_deuda_pago_dep_id_seq;

CREATE TABLE public.fin_deuda_pago (
                                       dep_id INTEGER NOT NULL DEFAULT nextval('public.fin_deuda_pago_dep_id_seq'),
                                       dep_valor_pagado NUMERIC(10,3),
                                       deu_id INTEGER NOT NULL,
                                       pag_id INTEGER NOT NULL,
                                       CONSTRAINT fin_deuda_pago_pk PRIMARY KEY (dep_id)
);


ALTER SEQUENCE public.fin_deuda_pago_dep_id_seq OWNED BY public.fin_deuda_pago.dep_id;

CREATE SEQUENCE public.ctr_reunion_reu_id_seq;

CREATE TABLE public.ctr_reunion (
                                    reu_id INTEGER NOT NULL DEFAULT nextval('public.ctr_reunion_reu_id_seq'),
                                    usu_cedula VARCHAR(10) NOT NULL,
                                    reu_fecha DATE NOT NULL,
                                    reu_estado BOOLEAN NOT NULL,
                                    lug_id INTEGER NOT NULL,
                                    CONSTRAINT ctr_reunion_pk PRIMARY KEY (reu_id)
);


ALTER SEQUENCE public.ctr_reunion_reu_id_seq OWNED BY public.ctr_reunion.reu_id;

CREATE SEQUENCE public.ctr_anuncio_anc_id_seq;

CREATE TABLE public.ctr_anuncio (
                                    anc_id INTEGER NOT NULL DEFAULT nextval('public.ctr_anuncio_anc_id_seq'),
                                    usu_cedula VARCHAR(10) NOT NULL,
                                    anc_titulo VARCHAR(50) NOT NULL,
                                    anc_descripcion VARCHAR(200) NOT NULL,
                                    anc_fecha_publicacion DATE NOT NULL,
                                    anc_prioridad VARCHAR(10) NOT NULL,
                                    anc_estado BOOLEAN NOT NULL,
                                    tan_id INTEGER NOT NULL,
                                    CONSTRAINT ctr_anuncio_pk PRIMARY KEY (anc_id)
);


ALTER SEQUENCE public.ctr_anuncio_anc_id_seq OWNED BY public.ctr_anuncio.anc_id;

CREATE TABLE public.ctr_casa (
                                 cas_id VARCHAR(6) NOT NULL,
                                 usu_cedula VARCHAR(10) NOT NULL,
                                 cas_ocupado BOOLEAN NOT NULL,
                                 CONSTRAINT ctr_casa_pk PRIMARY KEY (cas_id)
);


CREATE SEQUENCE public.ctr_reservacion_res_id_seq;

CREATE TABLE public.ctr_reservacion (
                                        res_id INTEGER NOT NULL DEFAULT nextval('public.ctr_reservacion_res_id_seq'),
                                        lug_id INTEGER NOT NULL,
                                        usu_cedula VARCHAR(10) NOT NULL,
                                        res_fecha DATE,
                                        res_hora_inicio time with time zone NOT NULL,
                                        res_hora_fin time with time zone NOT NULL,
                                        res_aprobado BOOLEAN NOT NULL,
                                        res_activa BOOLEAN NOT NULL,
                                        CONSTRAINT ctr_reservacion_pk PRIMARY KEY (res_id)
);


ALTER SEQUENCE public.ctr_reservacion_res_id_seq OWNED BY public.ctr_reservacion.res_id;

CREATE SEQUENCE public.ctr_asignacion_rol_id_seq;

CREATE TABLE public.ctr_asignacion (
                                       asg_id INTEGER NOT NULL DEFAULT nextval('public.ctr_asignacion_rol_id_seq'),
                                       prf_id INTEGER NOT NULL,
                                       usu_cedula VARCHAR(10) NOT NULL,
                                       CONSTRAINT ctr_asignacion_pk PRIMARY KEY (asg_id)
);


ALTER SEQUENCE public.ctr_asignacion_rol_id_seq OWNED BY public.ctr_asignacion.asg_id;

ALTER TABLE public.fin_gastos ADD CONSTRAINT fin_tipo_servicio_fin_gastos_fk
    FOREIGN KEY (tse_id)
        REFERENCES public.fin_tipo_servicio (tse_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.fin_pago_servicios ADD CONSTRAINT fin_tipo_servicio_fin_pago_servicios_fk
    FOREIGN KEY (tse_id)
        REFERENCES public.fin_tipo_servicio (tse_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.fin_deuda_pago ADD CONSTRAINT fin_pago_fin_deuda_pago_fk
    FOREIGN KEY (pag_id)
        REFERENCES public.fin_pago (pag_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.fin_monto ADD CONSTRAINT fin_tipo_pago_fin_monto_fk
    FOREIGN KEY (tde_id)
        REFERENCES public.fin_tipo_deuda (tde_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.fin_deuda ADD CONSTRAINT fin_monto_fin_deuda_fk
    FOREIGN KEY (mon_id)
        REFERENCES public.fin_monto (mon_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.ctr_anuncio ADD CONSTRAINT ctr_tipo_anuncio_ctr_anuncio_fk
    FOREIGN KEY (tan_id)
        REFERENCES public.ctr_tipo_anuncio (tan_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.ctr_reservacion ADD CONSTRAINT ctr_lugar_ctr_reservacion_fk
    FOREIGN KEY (lug_id)
        REFERENCES public.ctr_lugar (lug_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.ctr_reunion ADD CONSTRAINT ctr_lugar_ctr_reunion_fk
    FOREIGN KEY (lug_id)
        REFERENCES public.ctr_lugar (lug_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.ctr_perfil ADD CONSTRAINT seg_modulo_ctr_perfil_fk
    FOREIGN KEY (mod_id)
        REFERENCES public.ctr_modulo (mod_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.ctr_asignacion ADD CONSTRAINT ctr_perfil_ctr_asignacion_fk
    FOREIGN KEY (prf_id)
        REFERENCES public.ctr_perfil (prf_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.ctr_asignacion ADD CONSTRAINT ctr_usuario_ctr_asignacion_fk
    FOREIGN KEY (usu_cedula)
        REFERENCES public.ctr_usuario (usu_cedula)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.ctr_reservacion ADD CONSTRAINT ctr_usuario_ctr_reservacion_fk
    FOREIGN KEY (usu_cedula)
        REFERENCES public.ctr_usuario (usu_cedula)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.ctr_casa ADD CONSTRAINT ctr_usuario_ctr_casa_fk
    FOREIGN KEY (usu_cedula)
        REFERENCES public.ctr_usuario (usu_cedula)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.ctr_anuncio ADD CONSTRAINT ctr_usuario_ctr_anuncio_fk
    FOREIGN KEY (usu_cedula)
        REFERENCES public.ctr_usuario (usu_cedula)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.ctr_reunion ADD CONSTRAINT ctr_usuario_ctr_reunion_fk
    FOREIGN KEY (usu_cedula)
        REFERENCES public.ctr_usuario (usu_cedula)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.fin_deuda ADD CONSTRAINT ctr_usuario_fin_deuda_fk
    FOREIGN KEY (usu_cedula)
        REFERENCES public.ctr_usuario (usu_cedula)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.fin_incidencia ADD CONSTRAINT ctr_usuario_fin_incidencia_fk
    FOREIGN KEY (usu_cedula)
        REFERENCES public.ctr_usuario (usu_cedula)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.fin_gastos ADD CONSTRAINT fin_incidencia_fin_gastos_fk
    FOREIGN KEY (inc_id)
        REFERENCES public.fin_incidencia (inc_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.fin_deuda_pago ADD CONSTRAINT fin_deuda_fin_deuda_pago_fk
    FOREIGN KEY (deu_id)
        REFERENCES public.fin_deuda (deu_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

create view fin_egresos_incidencias as
SELECT EXTRACT(year FROM gasto.gas_fecha) AS anio,
       EXTRACT(month FROM gasto.gas_fecha) AS mes,
       servicio.tse_id AS codigo,
       servicio.tse_nombre AS servicio,
       sum(gasto.gas_pago) AS valor,
       count(incidencia.*) AS incidencias,
       concat(EXTRACT(year FROM gasto.gas_fecha), '-', EXTRACT(month FROM gasto.gas_fecha), '-', servicio.tse_id) AS concat
FROM fin_tipo_servicio servicio
         FULL JOIN fin_gastos gasto ON gasto.tse_id = servicio.tse_id
         FULL JOIN fin_incidencia incidencia ON incidencia.inc_id = gasto.inc_id
WHERE servicio.tse_incidencia IS TRUE
GROUP BY (EXTRACT(year FROM gasto.gas_fecha)), (EXTRACT(month FROM gasto.gas_fecha)), servicio.tse_id, servicio.tse_nombre
ORDER BY (EXTRACT(year FROM gasto.gas_fecha)), (EXTRACT(month FROM gasto.gas_fecha));



create view fin_egresos_servicios as
SELECT EXTRACT(year FROM pago.pse_fecha_pago) AS anio,
       EXTRACT(month FROM pago.pse_fecha_pago) AS mes,
       servicio.tse_id AS codigo,
       servicio.tse_nombre AS servicio,
       sum(pago.pse_monto) AS valor,
       concat(EXTRACT(year FROM pago.pse_fecha_pago), '-', EXTRACT(month FROM pago.pse_fecha_pago), '-', servicio.tse_id) AS concat
FROM fin_tipo_servicio servicio
         FULL JOIN fin_pago_servicios pago ON pago.tse_id = servicio.tse_id
WHERE servicio.tse_incidencia IS FALSE
GROUP BY (EXTRACT(year FROM pago.pse_fecha_pago)), (EXTRACT(month FROM pago.pse_fecha_pago)), servicio.tse_id
ORDER BY (EXTRACT(year FROM pago.pse_fecha_pago)), (EXTRACT(month FROM pago.pse_fecha_pago));




create view fin_ingresos_gastos_mensuales as
SELECT EXTRACT(year FROM fnpago.pag_fecha) AS anio,
       EXTRACT(month FROM fnpago.pag_fecha) AS mes,
       sum(fnpago.pag_valor) AS valor,
       concat('PA') AS tipo,
       concat(EXTRACT(year FROM fnpago.pag_fecha), '-', EXTRACT(month FROM fnpago.pag_fecha), '-PA') AS fecha
FROM fin_pago fnpago
GROUP BY (EXTRACT(year FROM fnpago.pag_fecha)), (EXTRACT(month FROM fnpago.pag_fecha))
UNION
SELECT EXTRACT(year FROM gaspago.gas_fecha) AS anio,
       EXTRACT(month FROM gaspago.gas_fecha) AS mes,
       sum(gaspago.gas_pago) AS valor,
       concat('EI') AS tipo,
       concat(EXTRACT(year FROM gaspago.gas_fecha), '-', EXTRACT(month FROM gaspago.gas_fecha), '-EI') AS fecha
FROM fin_gastos gaspago
GROUP BY (EXTRACT(year FROM gaspago.gas_fecha)), (EXTRACT(month FROM gaspago.gas_fecha))
UNION
SELECT EXTRACT(year FROM pagoserv.pse_fecha_pago) AS anio,
       EXTRACT(month FROM pagoserv.pse_fecha_pago) AS mes,
       sum(pagoserv.pse_monto) AS valor,
       concat('ES') AS tipo,
       concat(EXTRACT(year FROM pagoserv.pse_fecha_pago), '-', EXTRACT(month FROM pagoserv.pse_fecha_pago), '-ES') AS fecha
FROM fin_pago_servicios pagoserv
GROUP BY (EXTRACT(year FROM pagoserv.pse_fecha_pago)), (EXTRACT(month FROM pagoserv.pse_fecha_pago))
ORDER BY 2;



CREATE VIEW fin_recibo as
	SELECT  
		dep.dep_id,
		usu.usu_cedula,
		usu.usu_apellidos,
		usu.usu_nombres,
		usu.usu_correo,
		pag.*,
		deu.deu_id,
		deu.deu_fecha_corte,
		tde.tde_nombre,
		mon.mon_valor-(SELECT COALESCE(sum(dep_valor_pagado), 0) FROM fin_deuda_pago 
					   WHERE deu_id=deu.deu_id AND pag_id < dep.pag_id)as rec_valor_adeudado,
		dep.dep_valor_pagado,
		mon.mon_valor-(SELECT COALESCE(sum(dep_valor_pagado), 0) FROM fin_deuda_pago 
					   WHERE deu_id=deu.deu_id AND pag_id < dep.pag_id) - dep.dep_valor_pagado as rec_saldo
	FROM 
		ctr_usuario usu,
		fin_deuda deu,
		fin_monto mon,
		fin_tipo_deuda tde,
		fin_deuda_pago dep,
		fin_pago pag
	WHERE
		usu.usu_cedula=deu.usu_cedula AND
		deu.mon_id=mon.mon_id AND
		mon.tde_id=tde.tde_id AND
		deu.deu_id=dep.deu_id AND
		dep.pag_id=pag.pag_id;




CREATE OR REPLACE FUNCTION finalizar_monto()
  RETURNS trigger AS
$BODY$
BEGIN
    IF EXISTS (SELECT * FROM fin_monto WHERE tde_id=NEW.tde_id AND mon_fecha_fin IS NULL) THEN
		UPDATE fin_monto SET mon_fecha_fin=CURRENT_DATE	
			WHERE tde_id=NEW.tde_id AND mon_fecha_fin IS NULL;
    END IF;
    RETURN NEW;
END;
$BODY$
language plpgsql;


CREATE TRIGGER trigger_cerrar_ultimo_monto
  BEFORE INSERT
  ON fin_monto
  FOR EACH ROW
  EXECUTE PROCEDURE finalizar_monto();