package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import com.restapi.siscondominio.financiero.business.dto.FinIncidenciaDTO;
import com.restapi.siscondominio.financiero.business.vo.*;
import com.restapi.siscondominio.financiero.persistence.entities.FinGasto;
import com.restapi.siscondominio.financiero.persistence.entities.FinIncidencia;
import com.restapi.siscondominio.financiero.persistence.repositories.FinIncidenciaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FinIncidenciaService {

    @Autowired
    private FinIncidenciaRepository finIncidenciaRepository;
    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;


    public Long save(FinIncidenciaVO vO) {
        try{
            FinIncidencia bean = new FinIncidencia();
            BeanUtils.copyProperties(vO, bean);
            bean.setFinGastos(new ArrayList<FinGasto>());
            bean.setUsuCedula(ctrUsuarioRepository.getById(vO.getUsuCedula()));
            bean.setIncFechaReporte(new Date());
            bean.setIncSolucionada(false);
            bean.setIncFechaSolucion(null);
            bean.setIncEvidenciaSolucion(null);
            bean = finIncidenciaRepository.save(bean);
            return bean.getIncId();
        }catch (Exception e){
            return null;
        }

    }

    public void update(Long id, FinIncidenciaUpdateVO vO) {
        try{
            FinIncidencia bean = requireOne(id);
            BeanUtils.copyProperties(vO, bean);
            finIncidenciaRepository.save(bean);
        }catch(Exception e){

        }

    }

    public void updateIndicenSolution(Long id, FinIncidenciaSolucionadaUpdateVO vO) {
        try{
            FinIncidencia bean = requireOne(id);
            BeanUtils.copyProperties(vO, bean);
            finIncidenciaRepository.save(bean);
        }catch(Exception e){

        }

    }


    public void solution(Long id, FinIncidenciaSolucionarVO vO){
        try{
            FinIncidencia bean = requireOne(id);
            BeanUtils.copyProperties(vO, bean);
            bean.setIncFechaSolucion(new Date());
            bean.setIncSolucionada(true);
            finIncidenciaRepository.save(bean);
        }catch (Exception e){

        }

    }

    public FinIncidenciaDTO getById(Long id) {
        try{
            FinIncidencia original = finIncidenciaRepository.getById(id);
            FinIncidenciaDTO incidenciaDTO= new FinIncidenciaDTO();
            incidenciaDTO.setIncId(original.getIncId());
            incidenciaDTO.setUsuCedula(original.getUsuCedula().getUsuCedula());
            incidenciaDTO.setIncFechaReporte(original.getIncFechaReporte());
            incidenciaDTO.setIncDescripcion(original.getIncDescripcion());
            incidenciaDTO.setIncEvidenciaIndicencia(original.getIncEvidenciaIndicencia());
            incidenciaDTO.setIncSolucionada(original.getIncSolucionada());
            incidenciaDTO.setIncFechaSolucion(original.getIncFechaSolucion());
            incidenciaDTO.setIncEvidenciaSolucion(original.getIncEvidenciaSolucion());
            return incidenciaDTO;
        }catch(Exception e){
           return null;
        }
    }

    public List<FinIncidenciaDTO> query() {

        try{
            List<FinIncidencia> listaIncidencias= finIncidenciaRepository.findAll();
            List<FinIncidenciaDTO> listaIncidenciasDTO= new ArrayList<FinIncidenciaDTO>();

            for(FinIncidencia incidencia: listaIncidencias){
                listaIncidenciasDTO.add(new FinIncidenciaDTO(
                        incidencia.getIncId(),
                        incidencia.getUsuCedula().getUsuCedula(),
                        incidencia.getIncFechaReporte(),
                        incidencia.getIncDescripcion(),
                        incidencia.getIncEvidenciaIndicencia(),
                        incidencia.getIncSolucionada(),
                        incidencia.getIncFechaSolucion(),
                        incidencia.getIncEvidenciaSolucion()
                ));
            }
            return listaIncidenciasDTO;
        }catch(Exception e){
            return null;
        }
    }

    public List<FinIncidenciaDTO> incidentsFixed() {

        try{
            List<FinIncidencia> listaIncidencias= finIncidenciaRepository.findAll();
            List<FinIncidenciaDTO> listaIncidenciasDTO= new ArrayList<FinIncidenciaDTO>();

            for(FinIncidencia incidencia: listaIncidencias){
                if(incidencia.getIncSolucionada()){
                    listaIncidenciasDTO.add(new FinIncidenciaDTO(
                            incidencia.getIncId(),
                            incidencia.getUsuCedula().getUsuCedula(),
                            incidencia.getIncFechaReporte(),
                            incidencia.getIncDescripcion(),
                            incidencia.getIncEvidenciaIndicencia(),
                            incidencia.getIncSolucionada(),
                            incidencia.getIncFechaSolucion(),
                            incidencia.getIncEvidenciaSolucion()
                    ));
                }

            }
            return listaIncidenciasDTO;
        }catch (Exception e){
            return null;
        }


    }

    public List<FinIncidenciaDTO> incidentsNotFixed() {
        try{
            List<FinIncidencia> listaIncidencias= finIncidenciaRepository.findAll();
            List<FinIncidenciaDTO> listaIncidenciasDTO= new ArrayList<FinIncidenciaDTO>();

            for(FinIncidencia incidencia: listaIncidencias){
                if(incidencia.getIncSolucionada().equals(false)){
                    listaIncidenciasDTO.add(new FinIncidenciaDTO(
                            incidencia.getIncId(),
                            incidencia.getUsuCedula().getUsuCedula(),
                            incidencia.getIncFechaReporte(),
                            incidencia.getIncDescripcion(),
                            incidencia.getIncEvidenciaIndicencia(),
                            incidencia.getIncSolucionada(),
                            incidencia.getIncFechaSolucion(),
                            incidencia.getIncEvidenciaSolucion()
                    ));
                }

            }
            return listaIncidenciasDTO;
        }catch(Exception e){
            return null;
        }
    }

    private FinIncidencia requireOne(Long id) {
        try{
            return finIncidenciaRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));

        }catch (Exception e){
           return null;
        }
        }
}
