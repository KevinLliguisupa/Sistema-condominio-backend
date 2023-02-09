package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinEgresosServiciosDTO;
import com.restapi.siscondominio.financiero.persistence.entities.FinEgresosServicios;
import com.restapi.siscondominio.financiero.persistence.repositories.FinEgresosServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinEgresosServiciosService {
    @Autowired
    private FinEgresosServiciosRepository finEgresosServiciosRepository;

    public List<FinEgresosServiciosDTO> findOutputsServices(Integer anio, Integer mes){
        try{
            List<FinEgresosServicios> listaEgresosServicios = finEgresosServiciosRepository.findAll();
            List<FinEgresosServiciosDTO> listaEgresosServiciosDTO = new ArrayList<FinEgresosServiciosDTO>();
            if(anio.equals(0) && mes.equals(0)){

                for (FinEgresosServicios egresos: listaEgresosServicios){
                    listaEgresosServiciosDTO.add(
                            new FinEgresosServiciosDTO(
                                  egresos.getAnio(),
                                  egresos.getMes(),
                                  egresos.getCodigo(),
                                  egresos.getServicio(),
                                  egresos.getValor()
                            )
                    );
                }

            } else if (!(anio.equals(0)) && mes.equals(0)) {

                for (FinEgresosServicios egresos: listaEgresosServicios){

                    if(egresos.getAnio().equals(new BigDecimal(anio))){
                        listaEgresosServiciosDTO.add(
                                new FinEgresosServiciosDTO(
                                        egresos.getAnio(),
                                        egresos.getMes(),
                                        egresos.getCodigo(),
                                        egresos.getServicio(),
                                        egresos.getValor()
                                )
                        );
                    }

                }

            } else if (!(anio.equals(0)) && !(mes.equals(0))) {

                for (FinEgresosServicios egresos: listaEgresosServicios){

                    if(egresos.getAnio().equals(new BigDecimal(anio))&& egresos.getMes().equals(new BigDecimal(mes))){
                        listaEgresosServiciosDTO.add(
                                new FinEgresosServiciosDTO(
                                        egresos.getAnio(),
                                        egresos.getMes(),
                                        egresos.getCodigo(),
                                        egresos.getServicio(),
                                        egresos.getValor()
                                )
                        );
                    }

                }

            }
            return listaEgresosServiciosDTO;

        }catch (Exception e){
            return new ArrayList<FinEgresosServiciosDTO>();
        }

    }
}
