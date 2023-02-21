package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import com.restapi.siscondominio.financiero.business.dto.FinPagoServiciosDTO;
import com.restapi.siscondominio.financiero.business.vo.FinPagoServiciosUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinPagoServiciosVO;
import com.restapi.siscondominio.financiero.persistence.documents.pagoServiciosDocuments;
import com.restapi.siscondominio.financiero.persistence.entities.FinPagoServicios;
import com.restapi.siscondominio.financiero.persistence.repositories.FinPagoServiciosRespositoryMD;
import com.restapi.siscondominio.financiero.persistence.repositories.FinPagoServiciosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FinPagoServiciosService {

    @Autowired
    private FinPagoServiciosRepository finPagoServiciosRepository;
    @Autowired
    private CtrUsuarioRepository ctrUsuarioRepository;
    @Autowired
    private FinPagoServiciosRespositoryMD finPagoServiciosRespositoryMD;

    private String saveImage(String imagenBase64,Long idPago, Long idServicio) {
        String id="Servicio_"+idPago+"_"+idServicio;
        finPagoServiciosRespositoryMD.save(new pagoServiciosDocuments(id,imagenBase64));
        return id;
    }

    public List<pagoServiciosDocuments> getAllImagenesPagos(){
        return  this.finPagoServiciosRespositoryMD.findAll();
    }

    public Long save(FinPagoServiciosVO vO) {
        FinPagoServicios bean = new FinPagoServicios();
        bean.setTseId(vO.getTseId());
        bean.setPseMonto(vO.getPseMonto());
        bean.setPseRecibo("");
        bean.setPseFechaPago(new Date());
        bean.setPseCedTesorero(vO.getPseCedTesorero());
        bean = finPagoServiciosRepository.save(bean);
        Long id = bean.getPseId();

        FinPagoServiciosUpdateVO updateVO= new FinPagoServiciosUpdateVO();
        updateVO.setPseRecibo(this.saveImage(vO.getPseRecibo(),id, vO.getTseId()));
        this.update(id,updateVO);
        return  id;
    }


    public void update(Long id, FinPagoServiciosUpdateVO vO) {
        FinPagoServicios bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        finPagoServiciosRepository.save(bean);
    }

    public void updateImagen(String id, FinPagoServiciosUpdateVO vO) {
        pagoServiciosDocuments bean = finPagoServiciosRespositoryMD.findById(id).get();
        bean.setImagenBinarizada(vO.getPseRecibo());
        finPagoServiciosRespositoryMD.save(bean);
    }

    public FinPagoServiciosDTO getById(Long id) {
        FinPagoServicios original = requireOne(id);
        FinPagoServiciosDTO pagoServiciosDTO=toDTO(original);

        pagoServiciosDTO.setPseNomTesorero(ctrUsuarioRepository.getById(original.getPseCedTesorero()).getUsuApellidos()+" "+ctrUsuarioRepository.getById(original.getPseCedTesorero()).getUsuNombres());
        return pagoServiciosDTO;
    }

    public List<FinPagoServiciosDTO> query() {

        try{
            List<FinPagoServicios> listaPagosServicios= finPagoServiciosRepository.findAll();
            List<CtrUsuario> listaUsuarios= ctrUsuarioRepository.findAll();
            List<FinPagoServiciosDTO> listaPagosServiciosDTO= new ArrayList<FinPagoServiciosDTO>();
            HashMap<String, CtrUsuario> mapUsuarios= new HashMap<>();

            for(CtrUsuario usuario: listaUsuarios){
                mapUsuarios.put(usuario.getUsuCedula(),usuario);
            }

            for(FinPagoServicios pagoservicios: listaPagosServicios){
                listaPagosServiciosDTO.add(new FinPagoServiciosDTO(
                        pagoservicios.getPseId(),
                        pagoservicios.getTseId(),
                        pagoservicios.getPseMonto(),
                        pagoservicios.getPseRecibo(),
                        pagoservicios.getPseFechaPago(),
                        pagoservicios.getPseCedTesorero(),
                        mapUsuarios.get(pagoservicios.getPseCedTesorero()).getUsuApellidos()+" "+mapUsuarios.get(pagoservicios.getPseCedTesorero()).getUsuNombres()

                ));
            }
            return  listaPagosServiciosDTO;
        }catch (Exception e){
            return new ArrayList<FinPagoServiciosDTO>();
        }
    }

    private FinPagoServiciosDTO toDTO(FinPagoServicios original) {
        FinPagoServiciosDTO bean = new FinPagoServiciosDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private FinPagoServicios requireOne(Long id) {
        return finPagoServiciosRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

}
