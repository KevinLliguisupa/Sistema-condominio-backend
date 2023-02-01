package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrPerfilDTO;
import com.restapi.siscondominio.control.business.services.CtrPerfilService;
import com.restapi.siscondominio.control.business.vo.CtrPerfilQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrPerfilUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrPerfilVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/control/perfil")
public class CtrPerfilController {

    @Autowired
    private CtrPerfilService ctrPerfilService;

    @PostMapping
    public String save(@Valid @RequestBody CtrPerfilVO vO) {
        return ctrPerfilService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        ctrPerfilService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody CtrPerfilUpdateVO vO) {
        ctrPerfilService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CtrPerfilDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return ctrPerfilService.getById(id);
    }

    @GetMapping
    public Page<CtrPerfilDTO> query(@Valid CtrPerfilQueryVO vO) {
        return ctrPerfilService.query(vO);
    }
}
