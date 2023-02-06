package com.restapi.siscondominio.financiero.business.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;


public class Servicio<ENTITIE, DTO> {


    public DTO toDTO(ENTITIE original) {
        return null;
    }

    public List<DTO> toListDTO(List<ENTITIE> original) {
        List<DTO> converted = new ArrayList<>();
        for (ENTITIE data : original) {
            converted.add(toDTO(data));
        }
        return converted;
    }

    public Page<DTO> toPageDTO(Page<ENTITIE> original) {
        return original.map(this::toDTO);
    }

    public Page<DTO> toPageDTO(List<ENTITIE> original) {
        List<DTO> list = toListDTO(original);
        int listSize = list.size() == 0 ? 1 : list.size();
        Pageable pageable = PageRequest.of(0, listSize);
        return new PageImpl<>(list, pageable, list.size());
    }
}
