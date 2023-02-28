--
-- PostgreSQL database dump
--

-- Dumped from database version 13.7
-- Dumped by pg_dump version 15.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: scorpiondb
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO scorpiondb;

--
-- Name: finalizar_monto(); Type: FUNCTION; Schema: public; Owner: scorpiondb
--

CREATE FUNCTION public.finalizar_monto() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    IF EXISTS (SELECT * FROM fin_monto WHERE tde_id=NEW.tde_id AND mon_fecha_fin IS NULL) THEN
		UPDATE fin_monto SET mon_fecha_fin=CURRENT_DATE	
			WHERE tde_id=NEW.tde_id AND mon_fecha_fin IS NULL;
    END IF;
    RETURN NEW;
END;
$$;


ALTER FUNCTION public.finalizar_monto() OWNER TO scorpiondb;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: ctr_anuncio; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.ctr_anuncio (
    anc_id integer NOT NULL,
    usu_cedula character varying(10) NOT NULL,
    anc_titulo character varying(50) NOT NULL,
    anc_descripcion character varying(200) NOT NULL,
    anc_fecha_publicacion date NOT NULL,
    anc_prioridad character varying(10) NOT NULL,
    anc_estado boolean NOT NULL,
    tan_id integer NOT NULL
);


ALTER TABLE public.ctr_anuncio OWNER TO scorpiondb;

--
-- Name: ctr_anuncio_anc_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.ctr_anuncio_anc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ctr_anuncio_anc_id_seq OWNER TO scorpiondb;

--
-- Name: ctr_anuncio_anc_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.ctr_anuncio_anc_id_seq OWNED BY public.ctr_anuncio.anc_id;


--
-- Name: ctr_asignacion; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.ctr_asignacion (
    asg_id integer NOT NULL,
    prf_id integer NOT NULL,
    usu_cedula character varying(10) NOT NULL
);


ALTER TABLE public.ctr_asignacion OWNER TO scorpiondb;

--
-- Name: ctr_asignacion_rol_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.ctr_asignacion_rol_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ctr_asignacion_rol_id_seq OWNER TO scorpiondb;

--
-- Name: ctr_asignacion_rol_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.ctr_asignacion_rol_id_seq OWNED BY public.ctr_asignacion.asg_id;


--
-- Name: ctr_casa; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.ctr_casa (
    cas_id character varying(6) NOT NULL,
    usu_cedula character varying(10) NOT NULL,
    cas_ocupado boolean NOT NULL
);


ALTER TABLE public.ctr_casa OWNER TO scorpiondb;

--
-- Name: ctr_lugar; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.ctr_lugar (
    lug_id integer NOT NULL,
    lug_nombre character varying(60) NOT NULL,
    lug_disponible boolean NOT NULL
);


ALTER TABLE public.ctr_lugar OWNER TO scorpiondb;

--
-- Name: ctr_lugar_lug_id_seq_1; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.ctr_lugar_lug_id_seq_1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ctr_lugar_lug_id_seq_1 OWNER TO scorpiondb;

--
-- Name: ctr_lugar_lug_id_seq_1; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.ctr_lugar_lug_id_seq_1 OWNED BY public.ctr_lugar.lug_id;


--
-- Name: ctr_modulo; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.ctr_modulo (
    mod_id integer NOT NULL,
    mod_nombre character varying(50) NOT NULL,
    mod_icono character varying(50)
);


ALTER TABLE public.ctr_modulo OWNER TO scorpiondb;

--
-- Name: ctr_modulo_mod_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.ctr_modulo_mod_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ctr_modulo_mod_id_seq OWNER TO scorpiondb;

--
-- Name: ctr_modulo_mod_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.ctr_modulo_mod_id_seq OWNED BY public.ctr_modulo.mod_id;


--
-- Name: ctr_perfil; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.ctr_perfil (
    prf_id integer NOT NULL,
    prf_nombre character varying NOT NULL,
    prf_ruta_acceso character varying(100) NOT NULL,
    mod_id integer NOT NULL
);


ALTER TABLE public.ctr_perfil OWNER TO scorpiondb;

--
-- Name: ctr_perfil_prf_id_seq_1; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.ctr_perfil_prf_id_seq_1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ctr_perfil_prf_id_seq_1 OWNER TO scorpiondb;

--
-- Name: ctr_perfil_prf_id_seq_1; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.ctr_perfil_prf_id_seq_1 OWNED BY public.ctr_perfil.prf_id;


--
-- Name: ctr_reservacion; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.ctr_reservacion (
    res_id integer NOT NULL,
    lug_id integer NOT NULL,
    usu_cedula character varying(10) NOT NULL,
    res_fecha date,
    res_hora_inicio time with time zone NOT NULL,
    res_hora_fin time with time zone NOT NULL,
    res_aprobado boolean NOT NULL,
    res_activa boolean NOT NULL
);


ALTER TABLE public.ctr_reservacion OWNER TO scorpiondb;

--
-- Name: ctr_reservacion_res_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.ctr_reservacion_res_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ctr_reservacion_res_id_seq OWNER TO scorpiondb;

--
-- Name: ctr_reservacion_res_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.ctr_reservacion_res_id_seq OWNED BY public.ctr_reservacion.res_id;


--
-- Name: ctr_reunion; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.ctr_reunion (
    reu_id integer NOT NULL,
    usu_cedula character varying(10) NOT NULL,
    reu_fecha date NOT NULL,
    reu_estado boolean NOT NULL,
    lug_id integer NOT NULL
);


ALTER TABLE public.ctr_reunion OWNER TO scorpiondb;

--
-- Name: ctr_reunion_reu_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.ctr_reunion_reu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ctr_reunion_reu_id_seq OWNER TO scorpiondb;

--
-- Name: ctr_reunion_reu_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.ctr_reunion_reu_id_seq OWNED BY public.ctr_reunion.reu_id;


--
-- Name: ctr_tipo_anuncio; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.ctr_tipo_anuncio (
    tan_id integer NOT NULL,
    tan_nombre character varying(60) NOT NULL
);


ALTER TABLE public.ctr_tipo_anuncio OWNER TO scorpiondb;

--
-- Name: ctr_tipo_anuncio_tip_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.ctr_tipo_anuncio_tip_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ctr_tipo_anuncio_tip_id_seq OWNER TO scorpiondb;

--
-- Name: ctr_tipo_anuncio_tip_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.ctr_tipo_anuncio_tip_id_seq OWNED BY public.ctr_tipo_anuncio.tan_id;


--
-- Name: ctr_usuario; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.ctr_usuario (
    usu_cedula character varying(10) NOT NULL,
    usu_apellidos character varying(80) NOT NULL,
    usu_nombres character varying(70) NOT NULL,
    usu_correo character varying NOT NULL,
    usu_telefono character varying(10) NOT NULL,
    usu_clave character varying NOT NULL,
    usu_estado boolean NOT NULL,
    usu_rol character varying[]
);


ALTER TABLE public.ctr_usuario OWNER TO scorpiondb;

--
-- Name: fin_deuda; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.fin_deuda (
    deu_id integer NOT NULL,
    deu_fecha_corte date NOT NULL,
    deu_saldo numeric(10,3) NOT NULL,
    deu_cancelado boolean NOT NULL,
    mon_id integer NOT NULL,
    usu_cedula character varying(10) NOT NULL
);


ALTER TABLE public.fin_deuda OWNER TO scorpiondb;

--
-- Name: fin_deuda_pago; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.fin_deuda_pago (
    dep_id integer NOT NULL,
    dep_valor_pagado numeric(10,3),
    deu_id integer NOT NULL,
    pag_id integer NOT NULL
);


ALTER TABLE public.fin_deuda_pago OWNER TO scorpiondb;

--
-- Name: fin_deuda_pago_dep_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.fin_deuda_pago_dep_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fin_deuda_pago_dep_id_seq OWNER TO scorpiondb;

--
-- Name: fin_deuda_pago_dep_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.fin_deuda_pago_dep_id_seq OWNED BY public.fin_deuda_pago.dep_id;


--
-- Name: fin_gastos; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.fin_gastos (
    gas_id integer NOT NULL,
    tse_id integer NOT NULL,
    inc_id integer NOT NULL,
    gas_ced_tesorero character varying NOT NULL,
    gas_pago numeric(10,3) NOT NULL,
    gas_fecha date NOT NULL,
    gas_recibo character varying NOT NULL
);


ALTER TABLE public.fin_gastos OWNER TO scorpiondb;

--
-- Name: fin_incidencia; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.fin_incidencia (
    inc_id integer NOT NULL,
    usu_cedula character varying(10) NOT NULL,
    inc_fecha_reporte date NOT NULL,
    inc_descripcion character varying NOT NULL,
    inc_evidencia_indicencia character varying NOT NULL,
    inc_solucionada boolean NOT NULL,
    inc_fecha_solucion date,
    inc_evidencia_solucion character varying
);


ALTER TABLE public.fin_incidencia OWNER TO scorpiondb;

--
-- Name: fin_tipo_servicio; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.fin_tipo_servicio (
    tse_id integer NOT NULL,
    tse_incidencia boolean NOT NULL,
    tse_nombre character varying NOT NULL,
    tse_descripcion character varying NOT NULL
);


ALTER TABLE public.fin_tipo_servicio OWNER TO scorpiondb;

--
-- Name: fin_egresos_incidencias; Type: VIEW; Schema: public; Owner: scorpiondb
--

CREATE VIEW public.fin_egresos_incidencias AS
 SELECT date_part('year'::text, gasto.gas_fecha) AS anio,
    date_part('month'::text, gasto.gas_fecha) AS mes,
    servicio.tse_id AS codigo,
    servicio.tse_nombre AS servicio,
    sum(gasto.gas_pago) AS valor,
    count(incidencia.*) AS incidencias,
    concat(date_part('year'::text, gasto.gas_fecha), '-', date_part('month'::text, gasto.gas_fecha), '-', servicio.tse_id) AS concat
   FROM ((public.fin_tipo_servicio servicio
     FULL JOIN public.fin_gastos gasto ON ((gasto.tse_id = servicio.tse_id)))
     FULL JOIN public.fin_incidencia incidencia ON ((incidencia.inc_id = gasto.inc_id)))
  WHERE (servicio.tse_incidencia IS TRUE)
  GROUP BY (date_part('year'::text, gasto.gas_fecha)), (date_part('month'::text, gasto.gas_fecha)), servicio.tse_id, servicio.tse_nombre
  ORDER BY (date_part('year'::text, gasto.gas_fecha)), (date_part('month'::text, gasto.gas_fecha));


ALTER TABLE public.fin_egresos_incidencias OWNER TO scorpiondb;

--
-- Name: fin_egresos_servicios; Type: VIEW; Schema: public; Owner: scorpiondb
--

CREATE VIEW public.fin_egresos_servicios AS
SELECT
    NULL::double precision AS anio,
    NULL::double precision AS mes,
    NULL::integer AS codigo,
    NULL::character varying AS servicio,
    NULL::numeric AS valor,
    NULL::text AS concat;


ALTER TABLE public.fin_egresos_servicios OWNER TO scorpiondb;

--
-- Name: fin_gastos_gas_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.fin_gastos_gas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fin_gastos_gas_id_seq OWNER TO scorpiondb;

--
-- Name: fin_gastos_gas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.fin_gastos_gas_id_seq OWNED BY public.fin_gastos.gas_id;


--
-- Name: fin_incidencia_inc_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.fin_incidencia_inc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fin_incidencia_inc_id_seq OWNER TO scorpiondb;

--
-- Name: fin_incidencia_inc_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.fin_incidencia_inc_id_seq OWNED BY public.fin_incidencia.inc_id;


--
-- Name: fin_pago; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.fin_pago (
    pag_id integer NOT NULL,
    pag_fecha date NOT NULL,
    pag_valor numeric(10,3) NOT NULL,
    ced_tesorero character varying NOT NULL
);


ALTER TABLE public.fin_pago OWNER TO scorpiondb;

--
-- Name: fin_pago_servicios; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.fin_pago_servicios (
    pse_id integer NOT NULL,
    tse_id integer NOT NULL,
    pse_monto numeric(10,2) NOT NULL,
    pse_recibo character varying NOT NULL,
    pse_fecha_pago date NOT NULL,
    pse_ced_tesorero character varying(10) NOT NULL
);


ALTER TABLE public.fin_pago_servicios OWNER TO scorpiondb;

--
-- Name: fin_ingresos_gastos_mensuales; Type: VIEW; Schema: public; Owner: scorpiondb
--

CREATE VIEW public.fin_ingresos_gastos_mensuales AS
 SELECT date_part('year'::text, fnpago.pag_fecha) AS anio,
    date_part('month'::text, fnpago.pag_fecha) AS mes,
    sum(fnpago.pag_valor) AS valor,
    concat('PA') AS tipo,
    concat(date_part('year'::text, fnpago.pag_fecha), '-', date_part('month'::text, fnpago.pag_fecha), '-PA') AS fecha
   FROM public.fin_pago fnpago
  GROUP BY (date_part('year'::text, fnpago.pag_fecha)), (date_part('month'::text, fnpago.pag_fecha))
UNION
 SELECT date_part('year'::text, gaspago.gas_fecha) AS anio,
    date_part('month'::text, gaspago.gas_fecha) AS mes,
    sum(gaspago.gas_pago) AS valor,
    concat('EI') AS tipo,
    concat(date_part('year'::text, gaspago.gas_fecha), '-', date_part('month'::text, gaspago.gas_fecha), '-EI') AS fecha
   FROM public.fin_gastos gaspago
  GROUP BY (date_part('year'::text, gaspago.gas_fecha)), (date_part('month'::text, gaspago.gas_fecha))
UNION
 SELECT date_part('year'::text, pagoserv.pse_fecha_pago) AS anio,
    date_part('month'::text, pagoserv.pse_fecha_pago) AS mes,
    sum(pagoserv.pse_monto) AS valor,
    concat('ES') AS tipo,
    concat(date_part('year'::text, pagoserv.pse_fecha_pago), '-', date_part('month'::text, pagoserv.pse_fecha_pago), '-ES') AS fecha
   FROM public.fin_pago_servicios pagoserv
  GROUP BY (date_part('year'::text, pagoserv.pse_fecha_pago)), (date_part('month'::text, pagoserv.pse_fecha_pago))
  ORDER BY 2;


ALTER TABLE public.fin_ingresos_gastos_mensuales OWNER TO scorpiondb;

--
-- Name: fin_monto; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.fin_monto (
    mon_id integer NOT NULL,
    mon_valor numeric(10,3) NOT NULL,
    mon_fecha_asignacion date NOT NULL,
    mon_fecha_fin date,
    tde_id integer NOT NULL
);


ALTER TABLE public.fin_monto OWNER TO scorpiondb;

--
-- Name: fin_monto_val_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.fin_monto_val_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fin_monto_val_id_seq OWNER TO scorpiondb;

--
-- Name: fin_monto_val_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.fin_monto_val_id_seq OWNED BY public.fin_monto.mon_id;


--
-- Name: fin_pago_abo_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.fin_pago_abo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fin_pago_abo_id_seq OWNER TO scorpiondb;

--
-- Name: fin_pago_abo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.fin_pago_abo_id_seq OWNED BY public.fin_pago.pag_id;


--
-- Name: fin_pago_ali_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.fin_pago_ali_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fin_pago_ali_id_seq OWNER TO scorpiondb;

--
-- Name: fin_pago_ali_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.fin_pago_ali_id_seq OWNED BY public.fin_deuda.deu_id;


--
-- Name: fin_pago_servicios_pse_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.fin_pago_servicios_pse_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fin_pago_servicios_pse_id_seq OWNER TO scorpiondb;

--
-- Name: fin_pago_servicios_pse_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.fin_pago_servicios_pse_id_seq OWNED BY public.fin_pago_servicios.pse_id;


--
-- Name: fin_tipo_deuda; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.fin_tipo_deuda (
    tde_id integer NOT NULL,
    tde_nombre character varying NOT NULL
);


ALTER TABLE public.fin_tipo_deuda OWNER TO scorpiondb;

--
-- Name: fin_recibo; Type: VIEW; Schema: public; Owner: scorpiondb
--

CREATE VIEW public.fin_recibo AS
 SELECT dep.dep_id,
    usu.usu_cedula,
    usu.usu_apellidos,
    usu.usu_nombres,
    usu.usu_correo,
    pag.pag_id,
    pag.pag_fecha,
    pag.pag_valor,
    pag.ced_tesorero,
    deu.deu_id,
    deu.deu_fecha_corte,
    tde.tde_nombre,
    (mon.mon_valor - ( SELECT COALESCE(sum(fin_deuda_pago.dep_valor_pagado), (0)::numeric) AS "coalesce"
           FROM public.fin_deuda_pago
          WHERE ((fin_deuda_pago.deu_id = deu.deu_id) AND (fin_deuda_pago.pag_id < dep.pag_id)))) AS rec_valor_adeudado,
    dep.dep_valor_pagado,
    ((mon.mon_valor - ( SELECT COALESCE(sum(fin_deuda_pago.dep_valor_pagado), (0)::numeric) AS "coalesce"
           FROM public.fin_deuda_pago
          WHERE ((fin_deuda_pago.deu_id = deu.deu_id) AND (fin_deuda_pago.pag_id < dep.pag_id)))) - dep.dep_valor_pagado) AS rec_saldo
   FROM public.ctr_usuario usu,
    public.fin_deuda deu,
    public.fin_monto mon,
    public.fin_tipo_deuda tde,
    public.fin_deuda_pago dep,
    public.fin_pago pag
  WHERE (((usu.usu_cedula)::text = (deu.usu_cedula)::text) AND (deu.mon_id = mon.mon_id) AND (mon.tde_id = tde.tde_id) AND (deu.deu_id = dep.deu_id) AND (dep.pag_id = pag.pag_id));


ALTER TABLE public.fin_recibo OWNER TO scorpiondb;

--
-- Name: fin_recibo_cabecera; Type: VIEW; Schema: public; Owner: scorpiondb
--

CREATE VIEW public.fin_recibo_cabecera AS
SELECT
    NULL::integer AS pag_id,
    NULL::character varying(10) AS usu_cedula,
    NULL::character varying(80) AS usu_apellidos,
    NULL::character varying(70) AS usu_nombres,
    NULL::character varying AS usu_correo,
    NULL::date AS pag_fecha,
    NULL::numeric(10,3) AS pag_valor,
    NULL::character varying AS ced_tesorero;


ALTER TABLE public.fin_recibo_cabecera OWNER TO scorpiondb;

--
-- Name: fin_tipo_pago_pag_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.fin_tipo_pago_pag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fin_tipo_pago_pag_id_seq OWNER TO scorpiondb;

--
-- Name: fin_tipo_pago_pag_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.fin_tipo_pago_pag_id_seq OWNED BY public.fin_tipo_deuda.tde_id;


--
-- Name: fin_tipo_servicio_tis_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.fin_tipo_servicio_tis_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fin_tipo_servicio_tis_id_seq OWNER TO scorpiondb;

--
-- Name: fin_tipo_servicio_tis_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.fin_tipo_servicio_tis_id_seq OWNED BY public.fin_tipo_servicio.tse_id;


--
-- Name: seg_bitacora; Type: TABLE; Schema: public; Owner: scorpiondb
--

CREATE TABLE public.seg_bitacora (
    bit_id integer NOT NULL,
    bit_ced_guardia character varying NOT NULL,
    bit_fecha date NOT NULL,
    bit_evento character varying NOT NULL,
    bit_ced_ingreso character varying NOT NULL,
    bit_descripcion character varying,
    bit_ingreso boolean NOT NULL
);


ALTER TABLE public.seg_bitacora OWNER TO scorpiondb;

--
-- Name: seg_bitacora_bit_id_seq; Type: SEQUENCE; Schema: public; Owner: scorpiondb
--

CREATE SEQUENCE public.seg_bitacora_bit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seg_bitacora_bit_id_seq OWNER TO scorpiondb;

--
-- Name: seg_bitacora_bit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scorpiondb
--

ALTER SEQUENCE public.seg_bitacora_bit_id_seq OWNED BY public.seg_bitacora.bit_id;


--
-- Name: ctr_anuncio anc_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_anuncio ALTER COLUMN anc_id SET DEFAULT nextval('public.ctr_anuncio_anc_id_seq'::regclass);


--
-- Name: ctr_asignacion asg_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_asignacion ALTER COLUMN asg_id SET DEFAULT nextval('public.ctr_asignacion_rol_id_seq'::regclass);


--
-- Name: ctr_lugar lug_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_lugar ALTER COLUMN lug_id SET DEFAULT nextval('public.ctr_lugar_lug_id_seq_1'::regclass);


--
-- Name: ctr_modulo mod_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_modulo ALTER COLUMN mod_id SET DEFAULT nextval('public.ctr_modulo_mod_id_seq'::regclass);


--
-- Name: ctr_perfil prf_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_perfil ALTER COLUMN prf_id SET DEFAULT nextval('public.ctr_perfil_prf_id_seq_1'::regclass);


--
-- Name: ctr_reservacion res_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_reservacion ALTER COLUMN res_id SET DEFAULT nextval('public.ctr_reservacion_res_id_seq'::regclass);


--
-- Name: ctr_reunion reu_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_reunion ALTER COLUMN reu_id SET DEFAULT nextval('public.ctr_reunion_reu_id_seq'::regclass);


--
-- Name: ctr_tipo_anuncio tan_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_tipo_anuncio ALTER COLUMN tan_id SET DEFAULT nextval('public.ctr_tipo_anuncio_tip_id_seq'::regclass);


--
-- Name: fin_deuda deu_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_deuda ALTER COLUMN deu_id SET DEFAULT nextval('public.fin_pago_ali_id_seq'::regclass);


--
-- Name: fin_deuda_pago dep_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_deuda_pago ALTER COLUMN dep_id SET DEFAULT nextval('public.fin_deuda_pago_dep_id_seq'::regclass);


--
-- Name: fin_gastos gas_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_gastos ALTER COLUMN gas_id SET DEFAULT nextval('public.fin_gastos_gas_id_seq'::regclass);


--
-- Name: fin_incidencia inc_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_incidencia ALTER COLUMN inc_id SET DEFAULT nextval('public.fin_incidencia_inc_id_seq'::regclass);


--
-- Name: fin_monto mon_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_monto ALTER COLUMN mon_id SET DEFAULT nextval('public.fin_monto_val_id_seq'::regclass);


--
-- Name: fin_pago pag_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_pago ALTER COLUMN pag_id SET DEFAULT nextval('public.fin_pago_abo_id_seq'::regclass);


--
-- Name: fin_pago_servicios pse_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_pago_servicios ALTER COLUMN pse_id SET DEFAULT nextval('public.fin_pago_servicios_pse_id_seq'::regclass);


--
-- Name: fin_tipo_deuda tde_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_tipo_deuda ALTER COLUMN tde_id SET DEFAULT nextval('public.fin_tipo_pago_pag_id_seq'::regclass);


--
-- Name: fin_tipo_servicio tse_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_tipo_servicio ALTER COLUMN tse_id SET DEFAULT nextval('public.fin_tipo_servicio_tis_id_seq'::regclass);


--
-- Name: seg_bitacora bit_id; Type: DEFAULT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.seg_bitacora ALTER COLUMN bit_id SET DEFAULT nextval('public.seg_bitacora_bit_id_seq'::regclass);


--
-- Data for Name: ctr_anuncio; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.ctr_anuncio (anc_id, usu_cedula, anc_titulo, anc_descripcion, anc_fecha_publicacion, anc_prioridad, anc_estado, tan_id) FROM stdin;
1	0850055237	Anuncio 	Descripción 	2023-02-11	Alta	t	8
2	0850055237	Mantenimiento de Polideportivo	Polideportivo necesita Mantenimiento	2023-02-18	Alta	t	3
3	0850055237	Mantenimiento de calle 36	calle 36 necesita Mantenimiento hola	2023-02-18	Baja	t	3
4	0850055237	Mantenimiento de fuga de agua	fuga de agua necesita Mantenimiento calle 36	2023-02-18	Alta	t	3
5	0850055237	Delincuencia Organizada	Familia de Guacho	2023-02-18	Alta	t	3
6	0850055237	Robo en la casa de Juanita 	8 personas Robando en la casa de Doña Juana 	2023-02-18	Alta	t	2
7	0850055237	Robo en la casa de Juanita	8 personas Robando en la casa de Doña Juana 	2023-02-21	Alta	t	1
8	0850055237	Anuncio de prueba	Descripción regular	2023-02-21	Media	t	1
9	0850055237	hola	hola ...	2023-02-21	Media	f	1
\.


--
-- Data for Name: ctr_asignacion; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.ctr_asignacion (asg_id, prf_id, usu_cedula) FROM stdin;
\.


--
-- Data for Name: ctr_casa; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.ctr_casa (cas_id, usu_cedula, cas_ocupado) FROM stdin;
\.


--
-- Data for Name: ctr_lugar; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.ctr_lugar (lug_id, lug_nombre, lug_disponible) FROM stdin;
3	Piscina	t
4	Jardin	t
6	Capilla	t
7	Capilla	t
1	Capilla	t
5	Casa comunal 01	t
10	Nuevo	t
\.


--
-- Data for Name: ctr_modulo; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.ctr_modulo (mod_id, mod_nombre, mod_icono) FROM stdin;
\.


--
-- Data for Name: ctr_perfil; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.ctr_perfil (prf_id, prf_nombre, prf_ruta_acceso, mod_id) FROM stdin;
\.


--
-- Data for Name: ctr_reservacion; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.ctr_reservacion (res_id, lug_id, usu_cedula, res_fecha, res_hora_inicio, res_hora_fin, res_aprobado, res_activa) FROM stdin;
2	6	1728848373	2023-02-12	13:00:00-05	14:00:00-05	f	t
3	6	1005003171	2023-02-12	05:00:00-05	06:00:00-05	f	t
1	6	0850055237	2023-02-12	06:30:00-05	12:00:00-05	t	t
4	4	0850055237	2023-02-23	22:37:00-05	00:35:00-05	f	t
5	5	0850055237	2023-03-01	23:36:00-05	00:36:00-05	f	f
6	3	0850055237	2023-02-22	02:00:00-05	06:00:00-05	t	f
7	3	0850055237	2023-02-21	01:00:00-05	05:00:00-05	t	f
\.


--
-- Data for Name: ctr_reunion; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.ctr_reunion (reu_id, usu_cedula, reu_fecha, reu_estado, lug_id) FROM stdin;
1	0850055237	2023-02-21	t	4
2	0850055237	2023-02-18	f	4
3	0850055237	2023-02-21	t	4
4	0850055237	2023-02-21	t	3
\.


--
-- Data for Name: ctr_tipo_anuncio; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.ctr_tipo_anuncio (tan_id, tan_nombre) FROM stdin;
1	Sugerencia
2	Queja
3	Solicitud
8	Test
\.


--
-- Data for Name: ctr_usuario; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.ctr_usuario (usu_cedula, usu_apellidos, usu_nombres, usu_correo, usu_telefono, usu_clave, usu_estado, usu_rol) FROM stdin;
0508974865	Ruade Alvear	Juan Carlos	jcruadea@gmail.com	0958475362	$2a$10$wwyuCqKwtGQOIv/ejpcBDOO0j85USsCPXKyAw0CsW34ksecwJV0ES	t	{presidente}
1728848373	Lliguisupa Ponce	Kevin Brayan	kblliguisupap@hotmail.com	0969796346	$2a$10$mN3tdn/3nuFbdc7u4fennO2ILpJtP6PwyF2mprgMSJpoNB4sS1iDy	t	{tesorero}
0850055237	Quintero	Charlie	darmiqp16@gmail.com	0994245254	$2a$10$nmmAc2RxdM14YFRps9Lz4eOEshziMfRmxeDc0xxpG3SsJjLA7VOcK	t	{condomino}
0102116381	Alba Ponce	Julio David	jdalbap@gmail.com	0985468751	$2a$10$i390UPzXGWlxhrZYvnmK8.fjUjzZT3q8p9bnbNiaEibHc3KqDBN7e	t	{secretario}
1005003171	a	b	b	100	$2a$10$BVu3LYUvjtjoveHBsRulM.xLfdHWcdAzp6yqtjTwYikHr1P65bt8q	t	{presidente}
\.


--
-- Data for Name: fin_deuda; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.fin_deuda (deu_id, deu_fecha_corte, deu_saldo, deu_cancelado, mon_id, usu_cedula) FROM stdin;
1	2021-01-01	0.000	t	6	1728848373
2	2021-02-01	0.000	t	6	1728848373
3	2021-03-01	0.000	t	6	1728848373
4	2021-04-01	0.000	t	6	1728848373
5	2021-05-01	0.000	t	6	1728848373
6	2021-06-01	0.000	t	6	1728848373
7	2021-07-01	0.000	t	6	1728848373
8	2021-08-01	0.000	t	6	1728848373
9	2021-09-01	0.000	t	6	1728848373
10	2021-10-01	0.000	t	6	1728848373
11	2021-11-01	0.000	t	6	1728848373
12	2021-12-01	0.000	t	6	1728848373
13	2022-01-01	0.000	t	8	1728848373
14	2022-02-01	0.000	t	8	1728848373
15	2022-03-01	0.000	t	8	1728848373
16	2022-04-01	0.000	t	8	1728848373
17	2022-05-01	0.000	t	8	1728848373
18	2022-06-01	0.000	t	8	1728848373
19	2022-07-01	0.000	t	8	1728848373
20	2022-08-01	0.000	t	8	1728848373
24	2022-10-05	10.000	f	5	1728848373
25	2022-11-01	110.000	f	8	1728848373
26	2022-11-05	10.000	f	5	1728848373
27	2022-12-01	110.000	f	8	1728848373
28	2022-12-05	10.000	f	5	1728848373
29	2023-01-01	115.000	f	9	1728848373
30	2023-01-05	15.000	f	7	1728848373
31	2023-02-01	115.000	f	9	1728848373
32	2023-02-05	15.000	f	7	1728848373
33	2021-01-01	0.000	t	6	0508974865
34	2021-02-01	0.000	t	6	0508974865
35	2021-03-01	0.000	t	6	0508974865
36	2021-04-01	0.000	t	6	0508974865
37	2021-05-01	0.000	t	6	0508974865
38	2021-06-01	0.000	t	6	0508974865
39	2021-07-01	0.000	t	6	0508974865
40	2021-08-01	0.000	t	6	0508974865
41	2021-09-01	0.000	t	6	0508974865
42	2021-10-01	0.000	t	6	0508974865
43	2021-11-01	0.000	t	6	0508974865
44	2021-12-01	0.000	t	6	0508974865
45	2022-01-01	0.000	t	8	0508974865
46	2022-02-01	0.000	t	8	0508974865
47	2022-03-01	0.000	t	8	0508974865
48	2022-04-01	0.000	t	8	0508974865
49	2022-05-01	0.000	t	8	0508974865
50	2022-06-01	0.000	t	8	0508974865
51	2022-07-01	0.000	t	8	0508974865
52	2022-08-01	0.000	t	8	0508974865
53	2022-09-01	0.000	t	8	0508974865
54	2022-10-01	0.000	t	8	0508974865
55	2022-11-01	0.000	t	8	0508974865
56	2022-12-01	0.000	t	8	0508974865
57	2023-01-01	115.000	f	9	0508974865
58	2023-01-05	15.000	f	7	0508974865
59	2023-02-01	115.000	f	9	0508974865
60	2023-02-05	15.000	f	7	0508974865
21	2022-09-01	0.000	t	8	1728848373
22	2022-09-05	0.000	t	5	1728848373
23	2022-10-01	45.000	f	8	1728848373
\.


--
-- Data for Name: fin_deuda_pago; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.fin_deuda_pago (dep_id, dep_valor_pagado, deu_id, pag_id) FROM stdin;
1	105.000	1	1
2	105.000	2	2
3	105.000	3	3
4	105.000	4	4
5	105.000	5	5
6	105.000	6	6
7	105.000	7	7
8	105.000	8	8
9	105.000	9	9
10	105.000	10	10
11	105.000	11	11
12	105.000	12	12
13	110.000	13	13
14	110.000	14	14
15	110.000	15	15
16	110.000	16	16
17	110.000	17	17
18	110.000	18	18
19	110.000	19	19
20	110.000	20	20
21	105.000	33	21
22	105.000	34	22
23	105.000	35	23
24	105.000	36	24
25	105.000	37	25
26	105.000	38	26
27	105.000	39	27
28	105.000	40	28
29	105.000	41	29
30	105.000	42	30
31	105.000	43	31
32	105.000	44	32
33	110.000	45	33
34	110.000	46	34
35	110.000	47	35
36	110.000	48	36
37	110.000	49	37
38	110.000	50	38
39	110.000	51	39
40	110.000	52	40
41	110.000	53	41
42	110.000	54	42
43	110.000	55	43
44	110.000	56	44
45	50.000	21	45
46	60.000	21	46
47	10.000	22	47
48	65.000	23	47
\.


--
-- Data for Name: fin_gastos; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.fin_gastos (gas_id, tse_id, inc_id, gas_ced_tesorero, gas_pago, gas_fecha, gas_recibo) FROM stdin;
2	2	19	1728848373	353.000	2023-02-20	Incidencia2
3	10	19	1728848373	32.000	2023-02-20	Incidencia3
\.


--
-- Data for Name: fin_incidencia; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.fin_incidencia (inc_id, usu_cedula, inc_fecha_reporte, inc_descripcion, inc_evidencia_indicencia, inc_solucionada, inc_fecha_solucion, inc_evidencia_solucion) FROM stdin;
19	1728848373	2023-02-20	Daño en el poste de basquet del ala este del condominio, además presenta eroción en la estructura	Incidencia_19	t	2023-02-20	Solucion_19
20	1728848373	2023-02-20	Fuga de agua en la sección oeste, frente a la casa N89	Incidencia_20	f	\N	\N
21	1728848373	2023-02-20	Grietas en la vereda frente a la casa N52	Incidencia_21	f	\N	\N
\.


--
-- Data for Name: fin_monto; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.fin_monto (mon_id, mon_valor, mon_fecha_asignacion, mon_fecha_fin, tde_id) FROM stdin;
1	90.000	2018-01-01	2018-12-31	1
2	5.500	2018-01-01	2020-12-31	2
3	95.000	2019-01-01	2019-12-31	1
4	100.000	2020-01-01	2020-12-31	1
5	10.000	2020-01-01	2022-12-31	2
6	105.000	2021-01-01	2021-12-31	1
7	15.000	2022-01-01	\N	2
8	110.000	2022-01-01	2022-12-31	1
9	115.000	2023-01-01	\N	1
\.


--
-- Data for Name: fin_pago; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.fin_pago (pag_id, pag_fecha, pag_valor, ced_tesorero) FROM stdin;
1	2021-01-03	105.000	0102116381
2	2021-02-03	105.000	0102116381
3	2021-03-04	105.000	0102116381
4	2021-04-03	105.000	0102116381
5	2021-05-01	105.000	0102116381
6	2021-06-03	105.000	0102116381
7	2021-07-03	105.000	0102116381
8	2021-08-02	105.000	0102116381
9	2021-09-02	105.000	0102116381
10	2021-10-01	105.000	0102116381
11	2021-11-03	105.000	0102116381
12	2021-12-02	105.000	0102116381
13	2022-01-03	110.000	0102116381
14	2022-02-01	110.000	0102116381
15	2022-03-01	110.000	0102116381
16	2022-04-02	110.000	0102116381
17	2022-05-01	110.000	0102116381
18	2022-06-01	110.000	0102116381
19	2022-07-04	110.000	0102116381
20	2022-08-03	110.000	0102116381
21	2021-01-03	105.000	0102116381
22	2021-02-03	105.000	0102116381
23	2021-03-04	105.000	0102116381
24	2021-04-03	105.000	0102116381
25	2021-05-01	105.000	0102116381
26	2021-06-03	105.000	0102116381
27	2021-07-03	105.000	0102116381
28	2021-08-02	105.000	0102116381
29	2021-09-02	105.000	0102116381
30	2021-10-01	105.000	0102116381
31	2021-11-03	105.000	0102116381
32	2021-12-02	105.000	0102116381
33	2022-01-03	110.000	0102116381
34	2022-02-01	110.000	0102116381
35	2022-03-01	110.000	0102116381
36	2022-04-02	110.000	0102116381
37	2022-05-01	110.000	0102116381
38	2022-06-01	110.000	0102116381
39	2022-07-04	110.000	0102116381
40	2022-08-03	110.000	0102116381
41	2022-09-01	110.000	0102116381
42	2022-10-03	110.000	0102116381
43	2022-11-02	110.000	0102116381
44	2022-12-04	110.000	0102116381
45	2023-02-22	50.000	0102116381
46	2023-02-22	60.000	0102116381
47	2023-02-22	75.000	0102116381
\.


--
-- Data for Name: fin_pago_servicios; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.fin_pago_servicios (pse_id, tse_id, pse_monto, pse_recibo, pse_fecha_pago, pse_ced_tesorero) FROM stdin;
41	1	199.65	Servicio_41_1	2023-02-20	1728848373
42	4	578.50	Servicio_42_4	2023-02-20	1728848373
43	5	280.00	Servicio_43_5	2023-02-20	1728848373
\.


--
-- Data for Name: fin_tipo_deuda; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.fin_tipo_deuda (tde_id, tde_nombre) FROM stdin;
1	Alicuota
2	Multa
\.


--
-- Data for Name: fin_tipo_servicio; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.fin_tipo_servicio (tse_id, tse_incidencia, tse_nombre, tse_descripcion) FROM stdin;
1	f	Pago de luz	Pago del alumbrado interno
2	t	Daño de juegos infantiles	Daño de juegos infantiles
6	t	Control de plagas	Exterminación de plagas
3	f	Pago de vigilancia	Pago al guardia
5	f	Limpieza de áreas verdes	Limpieza a jardines, parque y entrada
4	f	Pago de impuesto a la propiedad	Pago de impuesto a la propiedad
7	t	Reparación del arco de basquet	Reparación del arco de basquet
8	t	Reparación de bancas	Reparación de bancas
9	f	Pago de agua	Pago de agua para uso común
10	t	Pago a pintor de estructura	Pago al señor Álvaro Castillo
\.


--
-- Data for Name: seg_bitacora; Type: TABLE DATA; Schema: public; Owner: scorpiondb
--

COPY public.seg_bitacora (bit_id, bit_ced_guardia, bit_fecha, bit_evento, bit_ced_ingreso, bit_descripcion, bit_ingreso) FROM stdin;
1	1728848373	2023-02-11	Ingreso a casa	0850055237	Inquilino, Ninguna	t
2	1728848373	2023-02-11	Visita	0850023995	Invitado,  visita a la casa de la seño Mina Chulde	t
\.


--
-- Name: ctr_anuncio_anc_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.ctr_anuncio_anc_id_seq', 9, true);


--
-- Name: ctr_asignacion_rol_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.ctr_asignacion_rol_id_seq', 1, false);


--
-- Name: ctr_lugar_lug_id_seq_1; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.ctr_lugar_lug_id_seq_1', 10, true);


--
-- Name: ctr_modulo_mod_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.ctr_modulo_mod_id_seq', 1, false);


--
-- Name: ctr_perfil_prf_id_seq_1; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.ctr_perfil_prf_id_seq_1', 1, false);


--
-- Name: ctr_reservacion_res_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.ctr_reservacion_res_id_seq', 7, true);


--
-- Name: ctr_reunion_reu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.ctr_reunion_reu_id_seq', 4, true);


--
-- Name: ctr_tipo_anuncio_tip_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.ctr_tipo_anuncio_tip_id_seq', 10, true);


--
-- Name: fin_deuda_pago_dep_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.fin_deuda_pago_dep_id_seq', 48, true);


--
-- Name: fin_gastos_gas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.fin_gastos_gas_id_seq', 3, true);


--
-- Name: fin_incidencia_inc_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.fin_incidencia_inc_id_seq', 21, true);


--
-- Name: fin_monto_val_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.fin_monto_val_id_seq', 9, true);


--
-- Name: fin_pago_abo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.fin_pago_abo_id_seq', 47, true);


--
-- Name: fin_pago_ali_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.fin_pago_ali_id_seq', 60, true);


--
-- Name: fin_pago_servicios_pse_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.fin_pago_servicios_pse_id_seq', 43, true);


--
-- Name: fin_tipo_pago_pag_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.fin_tipo_pago_pag_id_seq', 2, true);


--
-- Name: fin_tipo_servicio_tis_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.fin_tipo_servicio_tis_id_seq', 10, true);


--
-- Name: seg_bitacora_bit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scorpiondb
--

SELECT pg_catalog.setval('public.seg_bitacora_bit_id_seq', 2, true);


--
-- Name: ctr_anuncio ctr_anuncio_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_anuncio
    ADD CONSTRAINT ctr_anuncio_pk PRIMARY KEY (anc_id);


--
-- Name: ctr_asignacion ctr_asignacion_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_asignacion
    ADD CONSTRAINT ctr_asignacion_pk PRIMARY KEY (asg_id);


--
-- Name: ctr_casa ctr_casa_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_casa
    ADD CONSTRAINT ctr_casa_pk PRIMARY KEY (cas_id);


--
-- Name: ctr_lugar ctr_lugar_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_lugar
    ADD CONSTRAINT ctr_lugar_pk PRIMARY KEY (lug_id);


--
-- Name: ctr_modulo ctr_modulo_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_modulo
    ADD CONSTRAINT ctr_modulo_pk PRIMARY KEY (mod_id);


--
-- Name: ctr_perfil ctr_perfil_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_perfil
    ADD CONSTRAINT ctr_perfil_pk PRIMARY KEY (prf_id);


--
-- Name: ctr_reservacion ctr_reservacion_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_reservacion
    ADD CONSTRAINT ctr_reservacion_pk PRIMARY KEY (res_id);


--
-- Name: ctr_reunion ctr_reunion_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_reunion
    ADD CONSTRAINT ctr_reunion_pk PRIMARY KEY (reu_id);


--
-- Name: ctr_tipo_anuncio ctr_tipo_anuncio_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_tipo_anuncio
    ADD CONSTRAINT ctr_tipo_anuncio_pk PRIMARY KEY (tan_id);


--
-- Name: ctr_usuario ctr_usuario_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_usuario
    ADD CONSTRAINT ctr_usuario_pk PRIMARY KEY (usu_cedula);


--
-- Name: fin_deuda_pago fin_deuda_pago_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_deuda_pago
    ADD CONSTRAINT fin_deuda_pago_pk PRIMARY KEY (dep_id);


--
-- Name: fin_deuda fin_deuda_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_deuda
    ADD CONSTRAINT fin_deuda_pk PRIMARY KEY (deu_id);


--
-- Name: fin_gastos fin_gastos_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_gastos
    ADD CONSTRAINT fin_gastos_pk PRIMARY KEY (gas_id);


--
-- Name: fin_incidencia fin_incidencia_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_incidencia
    ADD CONSTRAINT fin_incidencia_pk PRIMARY KEY (inc_id);


--
-- Name: fin_monto fin_monto_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_monto
    ADD CONSTRAINT fin_monto_pk PRIMARY KEY (mon_id);


--
-- Name: fin_pago fin_pago_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_pago
    ADD CONSTRAINT fin_pago_pk PRIMARY KEY (pag_id);


--
-- Name: fin_pago_servicios fin_pago_servicios_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_pago_servicios
    ADD CONSTRAINT fin_pago_servicios_pk PRIMARY KEY (pse_id);


--
-- Name: fin_tipo_deuda fin_tipo_deuda_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_tipo_deuda
    ADD CONSTRAINT fin_tipo_deuda_pk PRIMARY KEY (tde_id);


--
-- Name: fin_tipo_servicio fin_tipo_servicio_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_tipo_servicio
    ADD CONSTRAINT fin_tipo_servicio_pk PRIMARY KEY (tse_id);


--
-- Name: seg_bitacora seg_bitacora_pk; Type: CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.seg_bitacora
    ADD CONSTRAINT seg_bitacora_pk PRIMARY KEY (bit_id);


--
-- Name: ctr_usuario_idx; Type: INDEX; Schema: public; Owner: scorpiondb
--

CREATE UNIQUE INDEX ctr_usuario_idx ON public.ctr_usuario USING btree (usu_correo);


--
-- Name: fin_egresos_servicios _RETURN; Type: RULE; Schema: public; Owner: scorpiondb
--

CREATE OR REPLACE VIEW public.fin_egresos_servicios AS
 SELECT date_part('year'::text, pago.pse_fecha_pago) AS anio,
    date_part('month'::text, pago.pse_fecha_pago) AS mes,
    servicio.tse_id AS codigo,
    servicio.tse_nombre AS servicio,
    sum(pago.pse_monto) AS valor,
    concat(date_part('year'::text, pago.pse_fecha_pago), '-', date_part('month'::text, pago.pse_fecha_pago), '-', servicio.tse_id) AS concat
   FROM (public.fin_tipo_servicio servicio
     FULL JOIN public.fin_pago_servicios pago ON ((pago.tse_id = servicio.tse_id)))
  WHERE (servicio.tse_incidencia IS FALSE)
  GROUP BY (date_part('year'::text, pago.pse_fecha_pago)), (date_part('month'::text, pago.pse_fecha_pago)), servicio.tse_id
  ORDER BY (date_part('year'::text, pago.pse_fecha_pago)), (date_part('month'::text, pago.pse_fecha_pago));


--
-- Name: fin_recibo_cabecera _RETURN; Type: RULE; Schema: public; Owner: scorpiondb
--

CREATE OR REPLACE VIEW public.fin_recibo_cabecera AS
 SELECT pag.pag_id,
    usu.usu_cedula,
    usu.usu_apellidos,
    usu.usu_nombres,
    usu.usu_correo,
    pag.pag_fecha,
    pag.pag_valor,
    pag.ced_tesorero
   FROM public.fin_pago pag,
    public.fin_deuda_pago dep,
    public.fin_deuda deu,
    public.ctr_usuario usu
  WHERE ((pag.pag_id = dep.pag_id) AND (dep.deu_id = deu.deu_id) AND ((deu.usu_cedula)::text = (usu.usu_cedula)::text))
  GROUP BY pag.pag_id, usu.usu_cedula;


--
-- Name: fin_monto trigger_cerrar_ultimo_monto; Type: TRIGGER; Schema: public; Owner: scorpiondb
--

CREATE TRIGGER trigger_cerrar_ultimo_monto BEFORE INSERT ON public.fin_monto FOR EACH ROW EXECUTE FUNCTION public.finalizar_monto();


--
-- Name: ctr_reservacion ctr_lugar_ctr_reservacion_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_reservacion
    ADD CONSTRAINT ctr_lugar_ctr_reservacion_fk FOREIGN KEY (lug_id) REFERENCES public.ctr_lugar(lug_id);


--
-- Name: ctr_reunion ctr_lugar_ctr_reunion_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_reunion
    ADD CONSTRAINT ctr_lugar_ctr_reunion_fk FOREIGN KEY (lug_id) REFERENCES public.ctr_lugar(lug_id);


--
-- Name: ctr_asignacion ctr_perfil_ctr_asignacion_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_asignacion
    ADD CONSTRAINT ctr_perfil_ctr_asignacion_fk FOREIGN KEY (prf_id) REFERENCES public.ctr_perfil(prf_id);


--
-- Name: ctr_anuncio ctr_tipo_anuncio_ctr_anuncio_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_anuncio
    ADD CONSTRAINT ctr_tipo_anuncio_ctr_anuncio_fk FOREIGN KEY (tan_id) REFERENCES public.ctr_tipo_anuncio(tan_id);


--
-- Name: ctr_anuncio ctr_usuario_ctr_anuncio_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_anuncio
    ADD CONSTRAINT ctr_usuario_ctr_anuncio_fk FOREIGN KEY (usu_cedula) REFERENCES public.ctr_usuario(usu_cedula);


--
-- Name: ctr_asignacion ctr_usuario_ctr_asignacion_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_asignacion
    ADD CONSTRAINT ctr_usuario_ctr_asignacion_fk FOREIGN KEY (usu_cedula) REFERENCES public.ctr_usuario(usu_cedula);


--
-- Name: ctr_casa ctr_usuario_ctr_casa_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_casa
    ADD CONSTRAINT ctr_usuario_ctr_casa_fk FOREIGN KEY (usu_cedula) REFERENCES public.ctr_usuario(usu_cedula);


--
-- Name: ctr_reservacion ctr_usuario_ctr_reservacion_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_reservacion
    ADD CONSTRAINT ctr_usuario_ctr_reservacion_fk FOREIGN KEY (usu_cedula) REFERENCES public.ctr_usuario(usu_cedula);


--
-- Name: ctr_reunion ctr_usuario_ctr_reunion_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_reunion
    ADD CONSTRAINT ctr_usuario_ctr_reunion_fk FOREIGN KEY (usu_cedula) REFERENCES public.ctr_usuario(usu_cedula);


--
-- Name: fin_deuda ctr_usuario_fin_deuda_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_deuda
    ADD CONSTRAINT ctr_usuario_fin_deuda_fk FOREIGN KEY (usu_cedula) REFERENCES public.ctr_usuario(usu_cedula);


--
-- Name: fin_incidencia ctr_usuario_fin_incidencia_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_incidencia
    ADD CONSTRAINT ctr_usuario_fin_incidencia_fk FOREIGN KEY (usu_cedula) REFERENCES public.ctr_usuario(usu_cedula);


--
-- Name: fin_deuda_pago fin_deuda_fin_deuda_pago_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_deuda_pago
    ADD CONSTRAINT fin_deuda_fin_deuda_pago_fk FOREIGN KEY (deu_id) REFERENCES public.fin_deuda(deu_id);


--
-- Name: fin_gastos fin_incidencia_fin_gastos_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_gastos
    ADD CONSTRAINT fin_incidencia_fin_gastos_fk FOREIGN KEY (inc_id) REFERENCES public.fin_incidencia(inc_id);


--
-- Name: fin_deuda fin_monto_fin_deuda_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_deuda
    ADD CONSTRAINT fin_monto_fin_deuda_fk FOREIGN KEY (mon_id) REFERENCES public.fin_monto(mon_id);


--
-- Name: fin_deuda_pago fin_pago_fin_deuda_pago_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_deuda_pago
    ADD CONSTRAINT fin_pago_fin_deuda_pago_fk FOREIGN KEY (pag_id) REFERENCES public.fin_pago(pag_id);


--
-- Name: fin_monto fin_tipo_pago_fin_monto_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_monto
    ADD CONSTRAINT fin_tipo_pago_fin_monto_fk FOREIGN KEY (tde_id) REFERENCES public.fin_tipo_deuda(tde_id);


--
-- Name: fin_gastos fin_tipo_servicio_fin_gastos_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_gastos
    ADD CONSTRAINT fin_tipo_servicio_fin_gastos_fk FOREIGN KEY (tse_id) REFERENCES public.fin_tipo_servicio(tse_id);


--
-- Name: fin_pago_servicios fin_tipo_servicio_fin_pago_servicios_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.fin_pago_servicios
    ADD CONSTRAINT fin_tipo_servicio_fin_pago_servicios_fk FOREIGN KEY (tse_id) REFERENCES public.fin_tipo_servicio(tse_id);


--
-- Name: ctr_perfil seg_modulo_ctr_perfil_fk; Type: FK CONSTRAINT; Schema: public; Owner: scorpiondb
--

ALTER TABLE ONLY public.ctr_perfil
    ADD CONSTRAINT seg_modulo_ctr_perfil_fk FOREIGN KEY (mod_id) REFERENCES public.ctr_modulo(mod_id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: scorpiondb
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

