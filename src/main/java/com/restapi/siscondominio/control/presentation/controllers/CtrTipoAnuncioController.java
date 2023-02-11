package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrLugarDTO;
import com.restapi.siscondominio.control.business.dto.CtrTipoAnuncioDTO;
import com.restapi.siscondominio.control.business.services.CtrTipoAnuncioService;
import com.restapi.siscondominio.control.business.vo.CtrLugarVO;
import com.restapi.siscondominio.control.business.vo.CtrTipoAnuncioQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrTipoAnuncioUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrTipoAnuncioVO;
import com.restapi.siscondominio.control.persistence.entities.CtrTipoAnuncio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/control/tipoanuncio")
public class CtrTipoAnuncioController {

    @Autowired
    private CtrTipoAnuncioService ctrTipoAnuncioService;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody CtrTipoAnuncioVO tipoAnuncioVO){

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(ctrTipoAnuncioService.guardarTipoAnuncio(tipoAnuncioVO));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el Lugar: "+ e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        ctrTipoAnuncioService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Long id,
                       @Valid @RequestBody CtrTipoAnuncioUpdateVO vO) {
        ctrTipoAnuncioService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CtrTipoAnuncioDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return ctrTipoAnuncioService.getById(id);
    }

    @GetMapping
    public ResponseEntity<List<CtrTipoAnuncioDTO>> findAll() {
        return ResponseEntity.ok(ctrTipoAnuncioService.findAll());
    }


}
