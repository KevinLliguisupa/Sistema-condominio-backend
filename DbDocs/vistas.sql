CREATE VIEW fin_recibo as
	SELECT  
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