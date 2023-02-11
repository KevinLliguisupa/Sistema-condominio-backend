package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrLugarDTO;
import com.restapi.siscondominio.control.business.dto.CtrReunionDTO;
import com.restapi.siscondominio.control.business.services.CtrLugarService;
import com.restapi.siscondominio.control.business.vo.*;
import com.restapi.siscondominio.control.persistence.entities.CtrLugar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/control/lugar")
public class CtrLugarController {

    @Autowired
    private CtrLugarService ctrLugarService;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody CtrLugarVO lugarVO){

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(ctrLugarService.guardarLugares(lugarVO));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el Lugar: "+ e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            ctrLugarService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Lugar eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el lugar: " + e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<Object> actualizarLugar( @RequestBody CtrLugarUpdateVO lugarUpdateVO) {

        try {
            CtrLugarDTO lugarDTO = ctrLugarService.actualizarLugar(lugarUpdateVO.getLugId(), lugarUpdateVO);
            return ResponseEntity.ok().body(lugarDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(ctrLugarService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CtrLugarDTO>> findAll() {
        return ResponseEntity.ok(ctrLugarService.findAll());
    }
}
