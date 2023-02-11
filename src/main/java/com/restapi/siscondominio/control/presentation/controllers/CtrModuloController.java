package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrCasaDTO;
import com.restapi.siscondominio.control.business.dto.CtrModuloDTO;
import com.restapi.siscondominio.control.business.dto.CtrUsuarioDTO;
import com.restapi.siscondominio.control.business.services.CtrModuloService;
import com.restapi.siscondominio.control.business.vo.*;
import com.restapi.siscondominio.control.persistence.entities.CtrModulo;
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
@RequestMapping("/control/modulo")
public class CtrModuloController {

    @Autowired
    private CtrModuloService ctrModuloService;


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            ctrModuloService.eliminarModule(id);
            return ResponseEntity.status(HttpStatus.OK).body("Módulo eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el módulo: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody CtrModuloVO moduloVO){

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(ctrModuloService.guardarModulo(moduloVO));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear módulo: "+ e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Object> actualizarModulo( @RequestBody CtrModuloUpdateVO moduloUpdateVO) {

        try {
            CtrModuloDTO moduloDTO = ctrModuloService.actulizarModulo(moduloUpdateVO);
            return ResponseEntity.ok().body(moduloDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<CtrModuloDTO>> findAll() {
        return ResponseEntity.ok(ctrModuloService.findAll());
    }
}
