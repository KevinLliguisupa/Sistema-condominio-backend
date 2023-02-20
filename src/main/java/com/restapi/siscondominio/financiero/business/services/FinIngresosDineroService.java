package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import com.restapi.siscondominio.financiero.business.dto.FinIngresosDineroDTO;
import com.restapi.siscondominio.financiero.persistence.entities.FinDeudaPago;
import com.restapi.siscondominio.financiero.persistence.entities.FinPago;
import com.restapi.siscondominio.financiero.persistence.repositories.FinDeudaPagoRepository;
import com.restapi.siscondominio.financiero.persistence.repositories.FinPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FinIngresosDineroService {

    @Autowired
    private FinPagoRepository finPagoRepository;
    @Autowired
    private FinDeudaPagoRepository finDeudaPagoRepository;
    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;

    public List<FinIngresosDineroDTO> findAllIngresos(){
        List<FinPago> listaPagos= finPagoRepository.findAll();
        List<FinDeudaPago> listaDeudadsPagos= finDeudaPagoRepository.findAll();
        List<CtrUsuario> listaUsuarios= ctrUsuarioRepository.findAll();
        List<FinIngresosDineroDTO> listaIngresos= new ArrayList<FinIngresosDineroDTO>();

        HashMap<String, CtrUsuario> mapUsuarios = new HashMap<>();
        HashMap<Long, String> mapPagoUsuario = new HashMap<>();

        for(CtrUsuario usuario: listaUsuarios){
            mapUsuarios.put(usuario.getUsuCedula(),usuario);
        }

        for(FinDeudaPago deudaPago: listaDeudadsPagos){
            if(mapPagoUsuario.containsKey(deudaPago.getPag())==false){
                mapPagoUsuario.put(deudaPago.getPag().getPagId(),deudaPago.getDeu().getUsuCedula().getUsuCedula());
            }
        }

        for(FinPago pago: listaPagos){
            listaIngresos.add(new FinIngresosDineroDTO(
                    pago.getPagId(),
                    pago.getPagFecha(),
                    pago.getPagValor(),
                    pago.getCedTesorero(),
                    mapUsuarios.get(pago.getCedTesorero()).getUsuApellidos()+" "+mapUsuarios.get(pago.getCedTesorero()).getUsuNombres(),
                    mapPagoUsuario.get(pago.getPagId()),
                    mapUsuarios.get(mapPagoUsuario.get(pago.getPagId())).getUsuApellidos()+" "+mapUsuarios.get(mapPagoUsuario.get(pago.getPagId())).getUsuNombres()));

        }
        return listaIngresos;
    }

}
