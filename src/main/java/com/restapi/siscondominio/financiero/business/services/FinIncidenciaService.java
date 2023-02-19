package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import com.restapi.siscondominio.financiero.business.dto.FinIncidenciaDTO;
import com.restapi.siscondominio.financiero.business.vo.*;
import com.restapi.siscondominio.financiero.persistence.documents.evidenciaIncidenciasNoSolucionadasDocuments;
import com.restapi.siscondominio.financiero.persistence.entities.FinGasto;
import com.restapi.siscondominio.financiero.persistence.entities.FinIncidencia;
import com.restapi.siscondominio.financiero.persistence.repositories.FinEvidenciaIncidenciaNoSolucionadasRepositoryMD;
import com.restapi.siscondominio.financiero.persistence.repositories.FinEvidenciaIncidenciaSolucionadasRepositoryMD;
import com.restapi.siscondominio.financiero.persistence.repositories.FinIncidenciaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.restapi.siscondominio.financiero.persistence.documents.evidenciaIncidenciaSolucionadaDocuments;
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

    @Autowired
    private FinEvidenciaIncidenciaNoSolucionadasRepositoryMD finEvidenciaIncidenciaNoSolucionadasRepositoryMD;

    @Autowired
    private FinEvidenciaIncidenciaSolucionadasRepositoryMD finEvidenciaIncidenciaSolucionadasRepositoryMD;

    private String saveEvidenciaIncidencia(String imagenBase64, Long idIncidencia){
        String id="Incidencia_"+idIncidencia;
        this.finEvidenciaIncidenciaNoSolucionadasRepositoryMD.save(new evidenciaIncidenciasNoSolucionadasDocuments(id,imagenBase64));
        return id;
    }
    private String saveEvidenciaSolucion(String imagenBase64, Long idIncidencia){
        String id="Solucion_"+idIncidencia;
        this.finEvidenciaIncidenciaSolucionadasRepositoryMD.save(new evidenciaIncidenciaSolucionadaDocuments(id,imagenBase64));
        return id;
    }

    private void updateEvidenciaIncidencia(String imagenBase64, String idImagen){
        this. finEvidenciaIncidenciaNoSolucionadasRepositoryMD.save(new evidenciaIncidenciasNoSolucionadasDocuments(idImagen,imagenBase64));

    }
    private void updateEvidenciaSolucion(String imagenBase64, String idImagen){
        this. finEvidenciaIncidenciaSolucionadasRepositoryMD.save(new evidenciaIncidenciaSolucionadaDocuments(idImagen,imagenBase64));

    }
    public Long save(FinIncidenciaVO vO) {
        try{
            FinIncidencia bean = new FinIncidencia();
            bean.setIncEvidenciaIndicencia("");
            bean.setIncDescripcion(vO.getIncDescripcion());
            bean.setFinGastos(new ArrayList<FinGasto>());
            bean.setUsuCedula(ctrUsuarioRepository.getById(vO.getUsuCedula()));
            bean.setIncFechaReporte(new Date());
            bean.setIncSolucionada(false);
            bean.setIncFechaSolucion(null);
            bean.setIncEvidenciaSolucion(null);
            bean = finIncidenciaRepository.save(bean);
            Long id=bean.getIncId();

            FinIncidenciaUpdateVO updateVO = new FinIncidenciaUpdateVO();
            updateVO.setIncDescripcion(vO.getIncDescripcion());
            updateVO.setIncEvidenciaIndicencia(this.saveEvidenciaIncidencia(vO.getIncEvidenciaIndicencia(),id));

            this.update(id,updateVO);

            return id;
        }catch (Exception e){
            return null;
        }

    }

    private void update(Long id, FinIncidenciaUpdateVO vO) {
        try{
            FinIncidencia bean = requireOne(id);
            BeanUtils.copyProperties(vO, bean);
            finIncidenciaRepository.save(bean);
        }catch(Exception e){

        }

    }
    public void updateIncidentNoSolution(Long id, FinIncidenciaUpdateVO vO) {
        try{
            FinIncidencia bean = requireOne(id);
            bean.setIncDescripcion(vO.getIncDescripcion());
            bean.setIncEvidenciaIndicencia(this.finIncidenciaRepository.getById(id).getIncEvidenciaIndicencia());
            finIncidenciaRepository.save(bean);
            this.updateEvidenciaIncidencia(vO.getIncEvidenciaIndicencia(),this.finIncidenciaRepository.getById(id).getIncEvidenciaIndicencia());

        }catch(Exception e){

        }

    }

    public void updateIndicenSolution(Long id, FinIncidenciaSolucionadaUpdateVO vO) {
        try{
            FinIncidencia bean = requireOne(id);
            bean.setIncEvidenciaSolucion(this.finIncidenciaRepository.getById(id).getIncEvidenciaSolucion());
            finIncidenciaRepository.save(bean);
            this.updateEvidenciaSolucion(vO.getIncEvidenciaSolucion(),this.finIncidenciaRepository.getById(id).getIncEvidenciaSolucion());
        }catch(Exception e){

        }

    }


    public void solution(Long id, FinIncidenciaSolucionarVO vO){
        try{
            FinIncidencia bean = requireOne(id);
            BeanUtils.copyProperties(vO, bean);
            bean.setIncEvidenciaSolucion(this.saveEvidenciaSolucion((vO.getIncEvidenciaSolucion()),id));
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
                        this.ctrUsuarioRepository.getById(incidencia.getUsuCedula().getUsuCedula()).getUsuApellidos()+" "+this.ctrUsuarioRepository.getById(incidencia.getUsuCedula().getUsuCedula()).getUsuNombres(),
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
                            this.ctrUsuarioRepository.getById(incidencia.getUsuCedula().getUsuCedula()).getUsuApellidos()+" "+ this.ctrUsuarioRepository.getById(incidencia.getUsuCedula().getUsuCedula()).getUsuNombres(),
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
                            this.ctrUsuarioRepository.getById(incidencia.getUsuCedula().getUsuCedula()).getUsuApellidos()+" " +this.ctrUsuarioRepository.getById(incidencia.getUsuCedula().getUsuCedula()).getUsuNombres(),
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

    public List<evidenciaIncidenciasNoSolucionadasDocuments> getAllEvidenciasNoSolucionadas(){
        return this.finEvidenciaIncidenciaNoSolucionadasRepositoryMD.findAll();
    }

    public  List<evidenciaIncidenciaSolucionadaDocuments> getAllEvidenciasSolucionadas(){
        return this.finEvidenciaIncidenciaSolucionadasRepositoryMD.findAll();
    }
}
