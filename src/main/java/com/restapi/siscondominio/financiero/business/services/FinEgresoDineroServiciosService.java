package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import com.restapi.siscondominio.financiero.business.dto.FinEgresoDineroServiciosDTO;
import com.restapi.siscondominio.financiero.persistence.entities.FinPagoServicios;
import com.restapi.siscondominio.financiero.persistence.repositories.FinPagoServiciosRepository;
import com.restapi.siscondominio.financiero.persistence.repositories.FinTipoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinEgresoDineroServiciosService {
    @Autowired
    private FinPagoServiciosRepository finPagoServiciosRepository;
    @Autowired
    private FinTipoServicioRepository finTipoServicioRepository;
    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;

    public List<FinEgresoDineroServiciosDTO> findAllOutpustServices(){
        try{

            List<FinPagoServicios> listaPagosServicios= finPagoServiciosRepository.findAll();
            List<FinEgresoDineroServiciosDTO> listaPagosServiciosDTO= new ArrayList<FinEgresoDineroServiciosDTO>();

            for (FinPagoServicios pago: listaPagosServicios){
                listaPagosServiciosDTO.add(new FinEgresoDineroServiciosDTO(
                       finTipoServicioRepository.getById(pago.getTseId()).getTseNombre(),
                       pago.getPseMonto(),
                       pago.getPseFechaPago(),
                       pago.getPseCedTesorero(),
                       ctrUsuarioRepository.getById(pago.getPseCedTesorero()).getUsuApellidos()+" "+ctrUsuarioRepository.getById(pago.getPseCedTesorero()).getUsuNombres()
                ));

            }
            return listaPagosServiciosDTO;
        }catch (Exception e){
            return new ArrayList<FinEgresoDineroServiciosDTO>();
        }

    }
}
