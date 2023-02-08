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

    public BigDecimal OutputsIncidentsValueTotal() {
        try{
            BigDecimal inputs= new BigDecimal(0);
            List<FinIngresosGastosMensualesDTO> lista= this.findAllOutputsIncidents();
            for (FinIngresosGastosMensualesDTO valor: lista){
                inputs=inputs.add(valor.getValor());
            }
            return  inputs;
        }catch (Exception e){
            return new BigDecimal(0);
        }
    }

    public BigDecimal OutputsServicesValueTotal() {
        try{
            BigDecimal inputs= new BigDecimal(0);
            List<FinIngresosGastosMensualesDTO> lista= this.findAllOutputsServices();
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
            inputs=inputs.subtract(this.OutputsServicesValueTotal());
            inputs=inputs.subtract(this.OutputsIncidentsValueTotal());
            return  inputs;
        }catch (Exception e){
            return new BigDecimal(0);
        }
    }


}
