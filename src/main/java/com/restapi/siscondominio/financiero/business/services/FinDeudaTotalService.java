package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinDeudaTotalDTO;
import com.restapi.siscondominio.financiero.persistence.entities.FinDeuda;
import com.restapi.siscondominio.financiero.persistence.repositories.FinDeudaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FinDeudaTotalService {
    @Autowired
    private FinDeudaRepository finDeudaRepository;
    public FinDeudaTotalDTO calculateDebtTotal(){
        BigDecimal valorDeuda= new BigDecimal(0);
        List<FinDeuda> listaDeudas= finDeudaRepository.findAll();

        for (FinDeuda deuda: listaDeudas){
           valorDeuda=valorDeuda.add(deuda.getDeuSaldo());
        }

        FinDeudaTotalDTO deudaTotalDTO= new FinDeudaTotalDTO(valorDeuda);
        return deudaTotalDTO;
    }
}
