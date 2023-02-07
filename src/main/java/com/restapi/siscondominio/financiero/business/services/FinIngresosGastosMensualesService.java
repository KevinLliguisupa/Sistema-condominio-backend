package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinIngresosGastosMensualesDTO;
import com.restapi.siscondominio.financiero.persistence.entities.FinIngresosGastosMensuale;
import com.restapi.siscondominio.financiero.persistence.repositories.FinIngresosGastosMensualesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@Service
public class FinIngresosGastosMensualesService {

    @Autowired
    private FinIngresosGastosMensualesRepository finIngresosGastosMensualesRepository;


    public List<FinIngresosGastosMensualesDTO> findAllInputs() {
        try{
            List<FinIngresosGastosMensualesDTO> listaDTO = new ArrayList<FinIngresosGastosMensualesDTO>();
            List<FinIngresosGastosMensuale> lista= finIngresosGastosMensualesRepository.findAll();
            for (FinIngresosGastosMensuale valor: lista){
                if(valor.getTipo().equals("I")){
                    listaDTO.add(new FinIngresosGastosMensualesDTO(valor.getAnio(),valor.getMes(), valor.getValor()));
                }

            }
            return  listaDTO;
        }catch (Exception e){
            return null;
        }
    }
    public List<FinIngresosGastosMensualesDTO> findAllOutputs() {
        try{
            List<FinIngresosGastosMensualesDTO> listaDTO = new ArrayList<FinIngresosGastosMensualesDTO>();
            List<FinIngresosGastosMensuale> lista= finIngresosGastosMensualesRepository.findAll();
            for (FinIngresosGastosMensuale valor: lista){
                if(valor.getTipo().equals("G")){
                    listaDTO.add(new FinIngresosGastosMensualesDTO(valor.getAnio(),valor.getMes(), valor.getValor()));
                }

            }
            return  listaDTO;
        }catch (Exception e){
            return null;
        }
    }

    public BigDecimal InputsValueTotal() {
        try{
            BigDecimal inputs= new BigDecimal(0);
            List<FinIngresosGastosMensualesDTO> lista= this.findAllInputs();
            for (FinIngresosGastosMensualesDTO valor: lista){
                inputs= inputs.add(valor.getValor());
            }
            return  inputs;
        }catch (Exception e){
            return new BigDecimal(0);
        }
    }

    public BigDecimal OutputsValueTotal() {
        try{
            BigDecimal inputs= new BigDecimal(0);
            List<FinIngresosGastosMensualesDTO> lista= this.findAllOutputs();
            for (FinIngresosGastosMensualesDTO valor: lista){
                inputs=inputs.add(valor.getValor());
            }
            return  inputs;
        }catch (Exception e){
            return new BigDecimal(0);
        }
    }

    public BigDecimal BalanceValueTotal() {
        try{
            BigDecimal inputs= this.InputsValueTotal();
            inputs=inputs.subtract(this.OutputsValueTotal());
            return  inputs;
        }catch (Exception e){
            return new BigDecimal(0);
        }
    }


}
