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