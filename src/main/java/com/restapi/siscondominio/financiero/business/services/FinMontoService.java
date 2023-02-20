package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinMontoDTO;
import com.restapi.siscondominio.financiero.business.dto.FinTipoDeudaDTO;
import com.restapi.siscondominio.financiero.business.speficications.FinMontoSpecification;
import com.restapi.siscondominio.financiero.business.vo.FinMontoVO;
import com.restapi.siscondominio.financiero.persistence.entities.FinMonto;
import com.restapi.siscondominio.financiero.persistence.entities.FinTipoDeuda;
import com.restapi.siscondominio.financiero.persistence.repositories.FinMontoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FinMontoService extends Servicio<FinMonto, FinMontoDTO> {

    @Autowired
    private FinMontoRepository finMontoRepository;

    public Page<FinMontoDTO> getAll(@NotNull Integer size, Integer page) {
        Sort sorter = Sort.by("monFechaFin").descending();
        return toPageDTO(finMontoRepository.findAll(PageRequest.of(page, size, sorter)));

    }

    public List<FinMontoDTO> getAll() {
        Sort sorter = Sort.by("monFechaFin").descending();
        return toListDTO(finMontoRepository.findAll(sorter));
    }

    public FinMontoDTO getById(Long id) {
        FinMonto original = requireOne(id);
        return toDTO(original);
    }

    public FinMontoDTO save(FinMontoVO requestBody) {
        FinMonto bean = new FinMonto();
        BeanUtils.copyProperties(requestBody, bean);
        //Agregar fecha asignacion a la entitie
        bean.setMonFechaAsignacion(LocalDate.now());

        //Cambio de id a objeto
        FinTipoDeuda tipoDeuda = new FinTipoDeuda();
        tipoDeuda.setTdeId(requestBody.getTdeId());
        bean.setTipoDeuda(tipoDeuda);

        bean = finMontoRepository.save(bean);
        cambiarUltimoValor();
        return toDTO(bean);
    }

    private void cambiarUltimoValor() {

    }

    private FinMonto requireOne(Long id) {
        return finMontoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public FinMontoDTO toDTO(FinMonto original) {
        FinMontoDTO bean = null;
        try {
            bean = new FinMontoDTO();
            BeanUtils.copyProperties(original, bean);
            bean.setTipoDeuda(toFinTipoDeudaDTO(original.getTipoDeuda()));
        } catch (Exception e) {
            System.out.println("Fallo conversion de Entidad a DTO: " + e);
        }
        return bean;
    }

    private FinTipoDeudaDTO toFinTipoDeudaDTO(FinTipoDeuda original) {
        FinTipoDeudaDTO bean = null;
        try {
            bean = new FinTipoDeudaDTO();
            BeanUtils.copyProperties(original, bean);
        } catch (Exception e) {
            System.out.println("Fallo conversion de Entidad a DTO: " + e);
        }
        return bean;
    }


    public List<FinMontoDTO> getActivos() {
        Specification<FinMonto> isActive = Specification.where(FinMontoSpecification.isActive());
        return toListDTO(finMontoRepository.findAll(isActive));
    }

    public FinMontoDTO getActivosByTipo(Long tdeId) {
        Specification<FinMonto> activeAndTipo = Specification.where(FinMontoSpecification.isActive())
                .and(FinMontoSpecification.hasTipo(tdeId));
        return toDTO(finMontoRepository.findAll(activeAndTipo).get(0));
    }
}