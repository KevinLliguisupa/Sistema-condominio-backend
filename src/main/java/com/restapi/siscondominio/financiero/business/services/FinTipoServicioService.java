package com.restapi.siscondominio.financiero.business.services;
import com.restapi.siscondominio.financiero.business.dto.FinTipoServicioDTO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoServicioUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoServicioVO;
import com.restapi.siscondominio.financiero.persistence.repositories.FinTipoServicioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restapi.siscondominio.financiero.persistence.entities.FinTipoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FinTipoServicioService {

    @Autowired
    private FinTipoServicioRepository finTipoServicioRepository;

    public Long save(FinTipoServicioVO vO) {
        try {
            FinTipoServicio bean = new FinTipoServicio();
            BeanUtils.copyProperties(vO, bean);
            bean = finTipoServicioRepository.save(bean);
            return bean.getTseId();
        }catch (Exception e){
            return null;
        }

    }

    public void update(Long id, FinTipoServicioUpdateVO vO) {
        try {
            FinTipoServicio bean = requireOne(id);
            BeanUtils.copyProperties(vO, bean);
            finTipoServicioRepository.save(bean);
        }catch (Exception e){

        }

    }

    public FinTipoServicioDTO getById(Long id) {
        try {
            FinTipoServicio original = requireOne(id);
            return toDTO(original);
        }catch (Exception e){
            return null;
        }

    }

    public List<FinTipoServicioDTO> query() {
       try{
           List<FinTipoServicio> listaTiposServicios= finTipoServicioRepository.findAll();
           List<FinTipoServicioDTO> listaTiposServiciosDTO= new ArrayList<FinTipoServicioDTO>();

           for(FinTipoServicio tipoServicio:listaTiposServicios ){
               listaTiposServiciosDTO.add(new FinTipoServicioDTO(tipoServicio.getTseId(), tipoServicio.getTseNombre(), tipoServicio.getTseDescripcion(), tipoServicio.getTseIncidencia()));
           }
           return listaTiposServiciosDTO;
       }catch (Exception e){
           return null;
       }
    }

    private FinTipoServicioDTO toDTO(FinTipoServicio original) {
        try{
            FinTipoServicioDTO bean = new FinTipoServicioDTO();
            BeanUtils.copyProperties(original, bean);
            return bean;
        }catch (Exception e){
            return null;
        }
    }

    private FinTipoServicio requireOne(Long id) {
        try{
            return finTipoServicioRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
        }catch (Exception e){
            return null;
        }
    }
}
