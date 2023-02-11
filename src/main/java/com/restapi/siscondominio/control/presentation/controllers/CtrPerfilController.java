package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrLugarDTO;
import com.restapi.siscondominio.control.business.dto.CtrPerfilDTO;
import com.restapi.siscondominio.control.business.services.CtrPerfilService;
import com.restapi.siscondominio.control.business.vo.*;
import com.restapi.siscondominio.control.persistence.entities.CtrPerfil;
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
@RequestMapping("/control/perfil")
public class CtrPerfilController {

    @Autowired
    private CtrPerfilService ctrPerfilService;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody CtrPerfilVO perfilVO){

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(ctrPerfilService.guardarPerfil(perfilVO));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el perfil: "+ e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            ctrPerfilService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Perfil eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el Perfil: " + e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<Object> actualizarLugar( @RequestBody CtrPerfilUpdateVO perfilUpdateVO) {

        try {
            CtrPerfilDTO perfilDTO = ctrPerfilService.actualizarPerfil( perfilUpdateVO);
            return ResponseEntity.ok().body(perfilDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(ctrPerfilService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CtrPerfilDTO>> findAll() {
        return ResponseEntity.ok(ctrPerfilService.findAll());
    }
}
