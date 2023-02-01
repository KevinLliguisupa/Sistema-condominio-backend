package com.restapi.siscondominio.seguridad.presentation.controllers;

import com.restapi.siscondominio.seguridad.business.dto.SegBitacoraDTO;
import com.restapi.siscondominio.seguridad.business.services.SegBitacoraService;
import com.restapi.siscondominio.seguridad.business.vo.SegBitacoraQueryVO;
import com.restapi.siscondominio.seguridad.business.vo.SegBitacoraUpdateVO;
import com.restapi.siscondominio.seguridad.business.vo.SegBitacoraVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/seguridad/bitacora")
public class SegBitacoraController {

    @Autowired
    private SegBitacoraService segBitacoraService;

    @PostMapping
    public String save(@Valid @RequestBody SegBitacoraVO vO) {
        return segBitacoraService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        segBitacoraService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody SegBitacoraUpdateVO vO) {
        segBitacoraService.update(id, vO);
    }

    @GetMapping("/{id}")
    public SegBitacoraDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return segBitacoraService.getById(id);
    }

    @GetMapping
    public Page<SegBitacoraDTO> query(@Valid SegBitacoraQueryVO vO) {
        return segBitacoraService.query(vO);
    }
}
