
CREATE SEQUENCE public.seg_bitacora_bit_id_seq;

CREATE TABLE public.seg_bitacora (
                bit_id INTEGER NOT NULL DEFAULT nextval('public.seg_bitacora_bit_id_seq'),
                bit_ced_guardia VARCHAR NOT NULL,
                bit_fecha VARCHAR NOT NULL,
                bit_evento VARCHAR NOT NULL,
                bit_ced_ingreso VARCHAR NOT NULL,
                bit_descripcion VARCHAR,
                bit_ingreso BOOLEAN NOT NULL,
                CONSTRAINT seg_bitacora_pk PRIMARY KEY (bit_id)
);


ALTER SEQUENCE public.seg_bitacora_bit_id_seq OWNED BY public.seg_bitacora.bit_id;

CREATE TABLE public.fin_tipo_servicio (
                tse_id INTEGER NOT NULL,
                tse_nombre VARCHAR NOT NULL,
                tse_descripcion VARCHAR NOT NULL,
                CONSTRAINT fin_tipo_servicio_pk PRIMARY KEY (tse_id)
);


CREATE SEQUENCE public.fin_gastos_gas_id_seq;

CREATE TABLE public.fin_gastos (
                gas_id INTEGER NOT NULL DEFAULT nextval('public.fin_gastos_gas_id_seq'),
                gas_ced_tesorero VARCHAR NOT NULL,
                gas_pago NUMERIC(10,3) NOT NULL,
                gas_fecha DATE NOT NULL,
                gas_recibo VARCHAR NOT NULL,
                tse_id INTEGER NOT NULL,
                CONSTRAINT fin_gastos_pk PRIMARY KEY (gas_id)
);


ALTER SEQUENCE public.fin_gastos_gas_id_seq OWNED BY public.fin_gastos.gas_id;

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

CREATE TABLE public.ctr_perfil (
                prf_id INTEGER NOT NULL,
                prf_nombre VARCHAR NOT NULL,
                prf_ruta_acceso VARCHAR(100) NOT NULL,
                mod_id INTEGER NOT NULL,
                CONSTRAINT ctr_perfil_pk PRIMARY KEY (prf_id)
);


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
                res_hora_inicio DATE NOT NULL,
                res_hora_fin DATE NOT NULL,
                res_aprobado BOOLEAN NOT NULL,
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
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.fin_deuda_pago ADD CONSTRAINT fin_pago_fin_deuda_pago_fk
FOREIGN KEY (pag_id)
REFERENCES public.fin_pago (pag_id)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.fin_monto ADD CONSTRAINT fin_tipo_pago_fin_monto_fk
FOREIGN KEY (tde_id)
REFERENCES public.fin_tipo_deuda (tde_id)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.fin_deuda ADD CONSTRAINT fin_monto_fin_deuda_fk
FOREIGN KEY (mon_id)
REFERENCES public.fin_monto (mon_id)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.ctr_anuncio ADD CONSTRAINT ctr_tipo_anuncio_ctr_anuncio_fk
FOREIGN KEY (tan_id)
REFERENCES public.ctr_tipo_anuncio (tan_id)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.ctr_reservacion ADD CONSTRAINT ctr_lugar_ctr_reservacion_fk
FOREIGN KEY (lug_id)
REFERENCES public.ctr_lugar (lug_id)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.ctr_reunion ADD CONSTRAINT ctr_lugar_ctr_reunion_fk
FOREIGN KEY (lug_id)
REFERENCES public.ctr_lugar (lug_id)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.ctr_perfil ADD CONSTRAINT seg_modulo_ctr_perfil_fk
FOREIGN KEY (mod_id)
REFERENCES public.ctr_modulo (mod_id)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.ctr_asignacion ADD CONSTRAINT ctr_perfil_ctr_asignacion_fk
FOREIGN KEY (prf_id)
REFERENCES public.ctr_perfil (prf_id)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.ctr_asignacion ADD CONSTRAINT ctr_usuario_ctr_asignacion_fk
FOREIGN KEY (usu_cedula)
REFERENCES public.ctr_usuario (usu_cedula)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.ctr_reservacion ADD CONSTRAINT ctr_usuario_ctr_reservacion_fk
FOREIGN KEY (usu_cedula)
REFERENCES public.ctr_usuario (usu_cedula)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.ctr_casa ADD CONSTRAINT ctr_usuario_ctr_casa_fk
FOREIGN KEY (usu_cedula)
REFERENCES public.ctr_usuario (usu_cedula)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.ctr_anuncio ADD CONSTRAINT ctr_usuario_ctr_anuncio_fk
FOREIGN KEY (usu_cedula)
REFERENCES public.ctr_usuario (usu_cedula)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.ctr_reunion ADD CONSTRAINT ctr_usuario_ctr_reunion_fk
FOREIGN KEY (usu_cedula)
REFERENCES public.ctr_usuario (usu_cedula)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.fin_deuda ADD CONSTRAINT ctr_usuario_fin_deuda_fk
FOREIGN KEY (usu_cedula)
REFERENCES public.ctr_usuario (usu_cedula)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.fin_deuda_pago ADD CONSTRAINT fin_deuda_fin_deuda_pago_fk
FOREIGN KEY (deu_id)
REFERENCES public.fin_deuda (deu_id)
ON DELETE NO ACTION
ON UPDATE CASCADE
NOT DEFERRABLE;
