package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrReunionDTO;
import com.restapi.siscondominio.control.business.services.CtrReunionService;
import com.restapi.siscondominio.control.business.vo.CtrReunionQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrReunionUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrReunionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/control/reunion")
public class CtrReunionController {

    @Autowired
    private CtrReunionService ctrReunionService;

    @PostMapping
    public String save(@Valid @RequestBody CtrReunionVO vO) {
        return ctrReunionService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        ctrReunionService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody CtrReunionUpdateVO vO) {
        ctrReunionService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CtrReunionDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return ctrReunionService.getById(id);
    }

    @GetMapping
    public Page<CtrReunionDTO> query(@Valid CtrReunionQueryVO vO) {
        return ctrReunionService.query(vO);
    }
}
