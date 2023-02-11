package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrReunionDTO;
import com.restapi.siscondominio.control.business.dto.CtrUsuarioDTO;
import com.restapi.siscondominio.control.business.services.CtrUsuarioService;
import com.restapi.siscondominio.control.business.vo.*;
import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
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
@RequestMapping("/control/usuario")
public class CtrUsuarioController {

    @Autowired
    private CtrUsuarioService ctrUsuarioService;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody CtrUsuarioVO usuarioVO){

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(ctrUsuarioService.guardarUsuario(usuarioVO));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario: "+ e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CtrUsuarioDTO>> findAllOrderDec() {
        return ResponseEntity.ok(ctrUsuarioService.findAllOrderDec());
    }

    @PutMapping
    public ResponseEntity<Object> actualizarUsuario( @RequestBody CtrUsuarioUpdateVO usuarioUpdateVO) {

        try {
            CtrUsuarioDTO usuarioDTO = ctrUsuarioService.actualizarUsuario( usuarioUpdateVO);
            return ResponseEntity.ok().body(usuarioDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping("/cedula/{id}")
    public ResponseEntity<?> EliminarUsuarioLog(@Valid @NotNull @PathVariable("id") String cedula) {
        try {
            ctrUsuarioService.eliminarUsuario(cedula);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la usuario: " + e.getMessage());
        }
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<?> ActivarUsuarioLog(@Valid @NotNull @PathVariable("id") String cedula) {
        try {
            ctrUsuarioService.activarUsuario(cedula);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario activado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al activar el Usuario: " + e.getMessage());
        }
    }










}
