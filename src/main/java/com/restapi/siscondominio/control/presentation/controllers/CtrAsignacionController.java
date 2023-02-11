package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrAsignacionDTO;
import com.restapi.siscondominio.control.business.services.CtrAsignacionService;
import com.restapi.siscondominio.control.business.vo.CtrAsignacionQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrAsignacionUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrAsignacionVO;
import com.restapi.siscondominio.control.business.vo.CtrPerfilVO;
import com.restapi.siscondominio.control.persistence.entities.CtrAsignacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/control/asignacion")
public class CtrAsignacionController {

    @Autowired
    private CtrAsignacionService ctrAsignacionService;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody CtrAsignacionVO asignacionVO){

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(ctrAsignacionService.guardarAsignacion(asignacionVO));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear una asignación: "+ e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            ctrAsignacionService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Asignación eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la asignación: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@Valid @NotNull @PathVariable("id") String cedula) {
        try {
            return ResponseEntity.ok(ctrAsignacionService.findByUsuCedula(cedula));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            return ResponseEntity.ok(ctrAsignacionService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

