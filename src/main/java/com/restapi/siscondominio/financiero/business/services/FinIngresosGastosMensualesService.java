package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinDeudaTotalDTO;
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
                if(valor.getTipo().equals("PA")){
                    listaDTO.add(new FinIngresosGastosMensualesDTO(valor.getAnio(),valor.getMes(), valor.getValor()));
                }

            }
            return  listaDTO;
        }catch (Exception e){
            return null;
        }
    }
    public List<FinIngresosGastosMensualesDTO> findAllOutputsIncidents() {
        try{
            List<FinIngresosGastosMensualesDTO> listaDTO = new ArrayList<FinIngresosGastosMensualesDTO>();
            List<FinIngresosGastosMensuale> lista= finIngresosGastosMensualesRepository.findAll();
            for (FinIngresosGastosMensuale valor: lista){
                if(valor.getTipo().equals("EI")){
                    listaDTO.add(new FinIngresosGastosMensualesDTO(valor.getAnio(),valor.getMes(), valor.getValor()));
                }

            }
            return  listaDTO;
        }catch (Exception e){
            return null;
        }
    }
    public List<FinIngresosGastosMensualesDTO> findAllOutputsServices() {
        try{
            List<FinIngresosGastosMensualesDTO> listaDTO = new ArrayList<FinIngresosGastosMensualesDTO>();
            List<FinIngresosGastosMensuale> lista= finIngresosGastosMensualesRepository.findAll();
            for (FinIngresosGastosMensuale valor: lista){
                if(valor.getTipo().equals("ES")){
                    listaDTO.add(new FinIngresosGastosMensualesDTO(valor.getAnio(),valor.getMes(), valor.getValor()));
                }

            }
            return  listaDTO;
        }catch (Exception e){
            return null;
        }
    }

    public FinDeudaTotalDTO InputsValueTotal() {
        try{
            BigDecimal inputs= new BigDecimal(0);
            List<FinIngresosGastosMensualesDTO> lista= this.findAllInputs();
            for (FinIngresosGastosMensualesDTO valor: lista){
                inputs= inputs.add(valor.getValor());
            }
            return  new FinDeudaTotalDTO(inputs);
        }catch (Exception e){
            return new FinDeudaTotalDTO(new BigDecimal(0));
        }
    }

    public FinDeudaTotalDTO OutputsIncidentsValueTotal() {
        try{
            BigDecimal inputs= new BigDecimal(0);
            List<FinIngresosGastosMensualesDTO> lista= this.findAllOutputsIncidents();
            for (FinIngresosGastosMensualesDTO valor: lista){
                inputs=inputs.add(valor.getValor());
            }
            return  new FinDeudaTotalDTO(inputs);
        }catch (Exception e){
            return new FinDeudaTotalDTO(new BigDecimal(0));
        }
    }

    public FinDeudaTotalDTO OutputsServicesValueTotal() {
        try{
            BigDecimal inputs= new BigDecimal(0);
            List<FinIngresosGastosMensualesDTO> lista= this.findAllOutputsServices();
            for (FinIngresosGastosMensualesDTO valor: lista){
                inputs=inputs.add(valor.getValor());
            }
            return  new FinDeudaTotalDTO(inputs);
        }catch (Exception e){
            return new FinDeudaTotalDTO(new BigDecimal(0));
        }
    }


    public FinDeudaTotalDTO BalanceValueTotal() {
        try{
            FinDeudaTotalDTO inputs= this.InputsValueTotal();
            inputs.setValor(inputs.getValor().subtract(this.OutputsServicesValueTotal().getValor()));
            inputs.setValor(inputs.getValor().subtract(this.OutputsIncidentsValueTotal().getValor()));

            return  new FinDeudaTotalDTO(inputs.getValor());
        }catch (Exception e){
            return new FinDeudaTotalDTO(new BigDecimal(0));
        }
    }


}
