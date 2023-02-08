package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import com.restapi.siscondominio.financiero.business.dto.FinEgresosDineroDTO;
import com.restapi.siscondominio.financiero.persistence.entities.FinGasto;
import com.restapi.siscondominio.financiero.persistence.repositories.FinGastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FinEgresosDineroService {

    @Autowired
    private FinGastoRepository finGastoRepository;

    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;

    public List<FinEgresosDineroDTO> findAllEgresos(){

        List<CtrUsuario> listaUsuarios= ctrUsuarioRepository.findAll();
        List<FinGasto> listaGastos= finGastoRepository.findAll();
        List<FinEgresosDineroDTO> listaEgresosDTO= new ArrayList<FinEgresosDineroDTO>();

        HashMap<String, CtrUsuario> mapUsuarios = new HashMap<>();

        for(CtrUsuario usuario: listaUsuarios){
            mapUsuarios.put(usuario.getUsuCedula(), usuario);
        }



         for(FinGasto gasto: listaGastos){
            listaEgresosDTO.add(new FinEgresosDineroDTO(
                    gasto.getGasCedTesorero(),
                    mapUsuarios.get(gasto.getGasCedTesorero()).getUsuApellidos() +" "+mapUsuarios.get(gasto.getGasCedTesorero()).getUsuNombres(),
                    gasto.getGasPago(),
                    gasto.getGasFecha(),
                    gasto.getIncId().getIncDescripcion(),
                    gasto.getTseId().getTseNombre()
            ));
        }

        return listaEgresosDTO;
    }

}
