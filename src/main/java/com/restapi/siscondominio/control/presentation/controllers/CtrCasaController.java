package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrCasaDTO;
import com.restapi.siscondominio.control.business.services.CtrCasaService;
import com.restapi.siscondominio.control.business.vo.CtrCasaQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrCasaUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrCasaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/control/casa")
public class CtrCasaController {

    @Autowired
    private CtrCasaService ctrCasaService;

    @PostMapping
    public String save(@Valid @RequestBody CtrCasaVO vO) {
        return ctrCasaService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        ctrCasaService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody CtrCasaUpdateVO vO) {
        ctrCasaService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CtrCasaDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return ctrCasaService.getById(id);
    }

    @GetMapping
    public Page<CtrCasaDTO> query(@Valid CtrCasaQueryVO vO) {
        return ctrCasaService.query(vO);
    }
}
