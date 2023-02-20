package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinEgresosIncidenciasDTO;
import com.restapi.siscondominio.financiero.persistence.entities.FinEgresosIncidencias;
import com.restapi.siscondominio.financiero.persistence.repositories.FinEgresosIncidenciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinEgresosIncidenciasService {
    @Autowired
    private FinEgresosIncidenciasRepository finEgresosIncidenciasRepository;

    public List<FinEgresosIncidenciasDTO> findOutputsIndicents(Integer anio, Integer mes){


            List<FinEgresosIncidencias> listaEgresosIncidencias = finEgresosIncidenciasRepository.findAll();
            List<FinEgresosIncidenciasDTO> listaEgresosIncidenciasDTO= new ArrayList<FinEgresosIncidenciasDTO>();

            if(anio.equals(0) && mes.equals(0)){
                for(FinEgresosIncidencias egresos: listaEgresosIncidencias){
                    listaEgresosIncidenciasDTO.add(new FinEgresosIncidenciasDTO(
                            egresos.getAnio(),
                            egresos.getMes(),
                            egresos.getCodigo(),
                            egresos.getServicio(),
                            egresos.getValor(),
                            egresos.getIncidencias()
                    ));
                }
            }else if(!(anio.equals(0)) && mes.equals(0)){

                for(FinEgresosIncidencias egresos: listaEgresosIncidencias){
                    if(egresos.getAnio().equals(new BigDecimal(anio))){
                        listaEgresosIncidenciasDTO.add(new FinEgresosIncidenciasDTO(
                                egresos.getAnio(),
                                egresos.getMes(),
                                egresos.getCodigo(),
                                egresos.getServicio(),
                                egresos.getValor(),
                                egresos.getIncidencias()
                        ));
                    }
                }
            }else if(!(anio.equals(0))&& !(mes.equals(0))){

                for(FinEgresosIncidencias egresos: listaEgresosIncidencias){

                    if(egresos.getAnio().equals(new BigDecimal(anio)) && egresos.getMes().equals(new BigDecimal(mes))){
                        listaEgresosIncidenciasDTO.add(new FinEgresosIncidenciasDTO(
                                egresos.getAnio(),
                                egresos.getMes(),
                                egresos.getCodigo(),
                                egresos.getServicio(),
                                egresos.getValor(),
                                egresos.getIncidencias()
                        ));
                    }


                }

            }

            return listaEgresosIncidenciasDTO;

    }
}
