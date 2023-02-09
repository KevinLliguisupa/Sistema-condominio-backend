package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrAnuncioDTO;
import com.restapi.siscondominio.control.business.dto.CtrReunionDTO;
import com.restapi.siscondominio.control.business.services.CtrReunionService;
import com.restapi.siscondominio.control.business.vo.*;
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
@RequestMapping("/control/reunion")
public class CtrReunionController {

    @Autowired
    private CtrReunionService ctrReunionService;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody CtrReunionVO reunionVO){

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(ctrReunionService.guardarReunion(reunionVO,reunionVO.getUsuCedula(), reunionVO.getLugId()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la reunión: "+ e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            ctrReunionService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Reunión eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la reunión: " + e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<Object> actualizarReunion( @RequestBody CtrReunionUpdateVO reunionVO) {

        try {
            CtrReunionDTO anuncioDTO = ctrReunionService.actualizarReunion(reunionVO.getReuId(), reunionVO, reunionVO.getLugId());
            return ResponseEntity.ok().body(anuncioDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(ctrReunionService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CtrReunionDTO>> findAll() {
        return ResponseEntity.ok(ctrReunionService.findAll());
    }

    @GetMapping("/ordenarDec")
    public ResponseEntity<List<CtrReunionDTO>> findAllOrderDec() {
        return ResponseEntity.ok(ctrReunionService.findAllOrderDec());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> EliminarReunionLog(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            ctrReunionService.eliminarReunionLog(id);
            return ResponseEntity.status(HttpStatus.OK).body("Reunión eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la reunión: " + e.getMessage());
        }
    }


}
