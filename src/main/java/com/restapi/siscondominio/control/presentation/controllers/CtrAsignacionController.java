package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrAsignacionDTO;
import com.restapi.siscondominio.control.business.services.CtrAsignacionService;
import com.restapi.siscondominio.control.business.vo.CtrAsignacionQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrAsignacionUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrAsignacionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/control/asignacion")
public class CtrAsignacionController {

    @Autowired
    private CtrAsignacionService ctrAsignacionService;

    @PostMapping
    public String save(@Valid @RequestBody CtrAsignacionVO vO) {
        return ctrAsignacionService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        ctrAsignacionService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody CtrAsignacionUpdateVO vO) {
        ctrAsignacionService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CtrAsignacionDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return ctrAsignacionService.getById(id);
    }

    @GetMapping
    public Page<CtrAsignacionDTO> query(@Valid CtrAsignacionQueryVO vO) {
        return ctrAsignacionService.query(vO);
    }
}
