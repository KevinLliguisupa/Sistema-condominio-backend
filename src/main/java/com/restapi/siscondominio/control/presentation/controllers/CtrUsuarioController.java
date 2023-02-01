package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrUsuarioDTO;
import com.restapi.siscondominio.control.business.services.CtrUsuarioService;
import com.restapi.siscondominio.control.business.vo.CtrUsuarioQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrUsuarioUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrUsuarioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/control/usuario")
public class CtrUsuarioController {

    @Autowired
    private CtrUsuarioService ctrUsuarioService;

    @PostMapping
    public String save(@Valid @RequestBody CtrUsuarioVO vO) {
        return ctrUsuarioService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        ctrUsuarioService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody CtrUsuarioUpdateVO vO) {
        ctrUsuarioService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CtrUsuarioDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return ctrUsuarioService.getById(id);
    }

    @GetMapping
    public Page<CtrUsuarioDTO> query(@Valid CtrUsuarioQueryVO vO) {
        return ctrUsuarioService.query(vO);
    }
}
