package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrLugarDTO;
import com.restapi.siscondominio.control.business.services.CtrLugarService;
import com.restapi.siscondominio.control.business.vo.CtrLugarQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrLugarUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrLugarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/control/lugar")
public class CtrLugarController {

    @Autowired
    private CtrLugarService ctrLugarService;

    @PostMapping
    public String save(@Valid @RequestBody CtrLugarVO vO) {
        return ctrLugarService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        ctrLugarService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody CtrLugarUpdateVO vO) {
        ctrLugarService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CtrLugarDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return ctrLugarService.getById(id);
    }

    @GetMapping
    public Page<CtrLugarDTO> query(@Valid CtrLugarQueryVO vO) {
        return ctrLugarService.query(vO);
    }
}
