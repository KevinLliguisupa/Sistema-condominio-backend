package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrReservacionDTO;
import com.restapi.siscondominio.control.business.services.CtrReservacionService;
import com.restapi.siscondominio.control.business.vo.CtrReservacionQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrReservacionUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrReservacionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/control/reservacion")
public class CtrReservacionController {

    @Autowired
    private CtrReservacionService ctrReservacionService;

    @PostMapping
    public String save(@Valid @RequestBody CtrReservacionVO vO) {
        return ctrReservacionService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        ctrReservacionService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody CtrReservacionUpdateVO vO) {
        ctrReservacionService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CtrReservacionDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return ctrReservacionService.getById(id);
    }

    @GetMapping
    public Page<CtrReservacionDTO> query(@Valid CtrReservacionQueryVO vO) {
        return ctrReservacionService.query(vO);
    }
}
