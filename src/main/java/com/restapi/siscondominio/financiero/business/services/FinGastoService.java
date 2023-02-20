package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import com.restapi.siscondominio.financiero.business.dto.FinGastoDTO;
import com.restapi.siscondominio.financiero.business.vo.FinGastoQueryVO;
import com.restapi.siscondominio.financiero.business.vo.FinGastoUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinGastoVO;
import com.restapi.siscondominio.financiero.persistence.documents.pagoIncidenciasDocuments;
import com.restapi.siscondominio.financiero.persistence.entities.FinGasto;
import com.restapi.siscondominio.financiero.persistence.entities.FinIncidencia;
import com.restapi.siscondominio.financiero.persistence.repositories.FinGastoRepository;
import com.restapi.siscondominio.financiero.persistence.repositories.FinIncidenciaRepository;
import com.restapi.siscondominio.financiero.persistence.repositories.FinPagoIncidenciasRespositoryMD;
import com.restapi.siscondominio.financiero.persistence.repositories.FinTipoServicioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.restapi.siscondominio.financiero.persistence.entities.FinTipoServicio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FinGastoService {

    @Autowired
    private FinGastoRepository finGastoRepository;
    @Autowired
    private FinIncidenciaRepository finIncidenciaRepository;
    @Autowired
    private FinTipoServicioRepository finTipoServicioRepository;
    @Autowired
    private FinPagoIncidenciasRespositoryMD finPagoIncidenciasRespositoryMD;
    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;
    private String saveImagenRecibo(String imagenBase64,Long idPago){
       String id="Incidencia"+idPago;
       this. finPagoIncidenciasRespositoryMD.save(new pagoIncidenciasDocuments(id,imagenBase64));
       return  id;
    }
    private void updateImagenRecibo(String imagenBase64,String idRecibo){
        this. finPagoIncidenciasRespositoryMD.save(new pagoIncidenciasDocuments(idRecibo,imagenBase64));
    }
    public Long save(FinGastoVO vO) {
        try{
            FinGasto bean = new FinGasto();
            BeanUtils.copyProperties(vO, bean);
            bean.setIncId(finIncidenciaRepository.getById(vO.getIncId()));
            bean.setTseId(finTipoServicioRepository.getById(vO.getTseId()));
            bean.setGasFecha(new Date());
            bean.setGasRecibo("");
            bean = finGastoRepository.save(bean);
            Long id= bean.getGasId();

            FinGastoUpdateVO updateVO= new FinGastoUpdateVO();
            updateVO.setGasRecibo(this.saveImagenRecibo(vO.getGasRecibo(),id));
            this.update(id,updateVO);

            return id;
        }catch (Exception e){
            return null;
        }

    }


    private void update(Long id, FinGastoUpdateVO vO) {
        try{
            FinGasto bean = requireOne(id);
            BeanUtils.copyProperties(vO, bean);
            finGastoRepository.save(bean);
        }catch (Exception e){

        }

    }

    public void updateRecibo(Long id, FinGastoUpdateVO vO) {
        try{
            FinGasto bean = this.finGastoRepository.getById(id);
            this.updateImagenRecibo(vO.getGasRecibo(),bean.getGasRecibo());
        }catch (Exception e){

        }

    }

    public FinGastoDTO getById(Long id) {
        try{
            FinGasto original = finGastoRepository.getById(id);
            FinGastoDTO gastoDTO = new FinGastoDTO();
            gastoDTO.setGasId(original.getGasId());
            gastoDTO.setTseId(original.getTseId().getTseId());
            gastoDTO.setIncId(original.getIncId().getIncId());
            gastoDTO.setGasCedTesorero(original.getGasCedTesorero());
            gastoDTO.setGasPago(original.getGasPago());
            gastoDTO.setGasFecha(original.getGasFecha());
            gastoDTO.setGasRecibo(original.getGasRecibo());
            return gastoDTO;
        }catch (Exception e){
            return null;
        }

    }

    public List<FinGastoDTO> query(Long incId) {
        try{
            List<FinGastoDTO> listaPagosPorIncidenciaDTO= new ArrayList<FinGastoDTO>();
            List<FinGasto> listaPagosPorIncidencia=finGastoRepository.findAll();

            for(FinGasto pago: listaPagosPorIncidencia){
                if(pago.getIncId().getIncId().equals(incId)){
                    listaPagosPorIncidenciaDTO.add(new FinGastoDTO(
                            pago.getGasId(),
                            pago.getGasCedTesorero(),
                            this.ctrUsuarioRepository.getById(pago.getGasCedTesorero()).getUsuApellidos()+" "+this.ctrUsuarioRepository.getById(pago.getGasCedTesorero()).getUsuApellidos(),
                            pago.getGasPago(),
                            pago.getGasFecha(),
                            pago.getGasRecibo(),
                            pago.getTseId().getTseId(),
                            pago.getIncId().getIncId()
                    ));
                }

            }

            return listaPagosPorIncidenciaDTO;
        }catch (Exception e){
            return null;
        }


    }

    public List<FinGastoDTO> query() {
        try{
            List<FinGastoDTO> listaPagosPorIncidenciaDTO= new ArrayList<FinGastoDTO>();
            List<FinGasto> listaPagosPorIncidencia=finGastoRepository.findAll();

            for(FinGasto pago: listaPagosPorIncidencia){

                listaPagosPorIncidenciaDTO.add(new FinGastoDTO(
                        pago.getGasId(),
                        pago.getGasCedTesorero(),
                        this.ctrUsuarioRepository.getById(pago.getGasCedTesorero()).getUsuApellidos()+" "+this.ctrUsuarioRepository.getById(pago.getGasCedTesorero()).getUsuApellidos(),
                        pago.getGasPago(),
                        pago.getGasFecha(),
                        pago.getGasRecibo(),
                        pago.getTseId().getTseId(),
                        pago.getIncId().getIncId()
                ));
            }

            return listaPagosPorIncidenciaDTO;
        }catch (Exception e){
            return null;
        }

    }


    private FinGasto requireOne(Long id) {
        try{
            return finGastoRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));

        }catch (Exception e){
           return null;
        }
        }
    public List <pagoIncidenciasDocuments> getAllRecibos(){
        return  this.finPagoIncidenciasRespositoryMD.findAll();
    }
}
