package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrCasaDTO;
import com.restapi.siscondominio.control.business.dto.CtrUsuarioDTO;
import com.restapi.siscondominio.control.business.services.CtrCasaService;
import com.restapi.siscondominio.control.business.vo.*;
import com.restapi.siscondominio.control.persistence.entities.CtrCasa;
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
@RequestMapping("/control/casa")
public class CtrCasaController {

    @Autowired
    private CtrCasaService ctrCasaService;

    @GetMapping
    public ResponseEntity<List<CtrCasaDTO>> findAllOrderDec() {
        return ResponseEntity.ok(ctrCasaService.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody CtrCasaVO casaVO){

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(ctrCasaService.guardarCasa(casaVO));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al asignar casa: "+ e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Object> actualizarUsuario( @RequestBody CtrCasaUpdateVO casaUpdateVO) {

        try {
            CtrCasaDTO casaDTO = ctrCasaService.actualizarCasa(casaUpdateVO);
            return ResponseEntity.ok().body(casaDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> EliminarUsuarioLog(@Valid @NotNull @PathVariable("id") String idCasa) {
        try {
            ctrCasaService.eliminarCasa(idCasa);
            return ResponseEntity.status(HttpStatus.OK).body("Casa eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar casa: " + e.getMessage());
        }
    }
    @PutMapping("/activar/{id}")
    public ResponseEntity<?> activarUsuarioLog(@Valid @NotNull @PathVariable("id") String idCasa) {
        try {
            ctrCasaService.activarCasa(idCasa);
            return ResponseEntity.status(HttpStatus.OK).body("Casa activada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al activar casa: " + e.getMessage());
        }
    }

}
