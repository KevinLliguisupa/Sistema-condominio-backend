package com.restapi.siscondominio.financiero.business.services;

import com.restapi.siscondominio.financiero.business.dto.FinTipoDeudaDTO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoDeudaVO;
import com.restapi.siscondominio.financiero.persistence.entities.FinTipoDeuda;
import com.restapi.siscondominio.financiero.persistence.repositories.FinTipoDeudaRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FinTipoDeudaService extends Servicio<FinTipoDeuda, FinTipoDeudaDTO>  {

    @Autowired
    private FinTipoDeudaRepository finTipoDeudaRepository;

    public Page<FinTipoDeudaDTO> getAll(@NotNull Boolean pagination, Integer size, Integer page) {
        Sort sorter = Sort.by("tdeNombre");
        if (pagination) {
            return toPageDTO(finTipoDeudaRepository.findAll(PageRequest.of(page, size, sorter)));
        }
        return toPageDTO(finTipoDeudaRepository.findAll(sorter));
    }

    public FinTipoDeudaDTO getById(Long id) {
        FinTipoDeuda original = requireOne(id);
        return toDTO(original);
    }

    public FinTipoDeudaDTO save(FinTipoDeudaVO body) {
        FinTipoDeuda bean = new FinTipoDeuda();
        BeanUtils.copyProperties(body, bean);
        bean = finTipoDeudaRepository.save(bean);
        return toDTO(bean);
    }

    public FinTipoDeudaDTO update(Long id, FinTipoDeudaVO body) {
        FinTipoDeuda bean = requireOne(id);
        BeanUtils.copyProperties(body, bean);
        finTipoDeudaRepository.save(bean);
        return toDTO(bean);
    }

    private FinTipoDeuda requireOne(Long id) {
        return finTipoDeudaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Recurso no encontrado: " + id));
    }
    public FinTipoDeudaDTO toDTO(FinTipoDeuda original) {
        FinTipoDeudaDTO bean = null;
        try {
            bean = new FinTipoDeudaDTO();
            BeanUtils.copyProperties(original, bean);
        } catch (Exception e) {
            System.out.println("Fallo conversion de Entidad a DTO: " + e);
        }
        return bean;
    }
}
