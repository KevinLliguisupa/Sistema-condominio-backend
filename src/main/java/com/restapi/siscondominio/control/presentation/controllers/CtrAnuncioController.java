package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrAnuncioDTO;
import com.restapi.siscondominio.control.business.services.CtrAnuncioService;
import com.restapi.siscondominio.control.business.vo.CtrAnuncioQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrAnuncioUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrAnuncioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/control/anuncio")
public class CtrAnuncioController {

    @Autowired
    private CtrAnuncioService ctrAnuncioService;

    @PostMapping
    public String save(@Valid @RequestBody CtrAnuncioVO vO) {
        return ctrAnuncioService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        ctrAnuncioService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody CtrAnuncioUpdateVO vO) {
        ctrAnuncioService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CtrAnuncioDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return ctrAnuncioService.getById(id);
    }

    @GetMapping
    public Page<CtrAnuncioDTO> query(@Valid CtrAnuncioQueryVO vO) {
        return ctrAnuncioService.query(vO);
    }
}
