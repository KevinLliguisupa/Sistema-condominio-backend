package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrModuloDTO;
import com.restapi.siscondominio.control.business.services.CtrModuloService;
import com.restapi.siscondominio.control.business.vo.CtrModuloQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrModuloUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrModuloVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/control/modulo")
public class CtrModuloController {

    @Autowired
    private CtrModuloService ctrModuloService;

    @PostMapping
    public String save(@Valid @RequestBody CtrModuloVO vO) {
        return ctrModuloService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        ctrModuloService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody CtrModuloUpdateVO vO) {
        ctrModuloService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CtrModuloDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return ctrModuloService.getById(id);
    }

    @GetMapping
    public Page<CtrModuloDTO> query(@Valid CtrModuloQueryVO vO) {
        return ctrModuloService.query(vO);
    }
}
