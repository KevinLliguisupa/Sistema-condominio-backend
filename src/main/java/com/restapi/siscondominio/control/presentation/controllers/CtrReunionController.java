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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/control/reunion")
public class CtrReunionController {

    @Autowired
    private CtrReunionService ctrReunionService;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody CtrReunionVO reunionVO){
        try {
            CtrReunionDTO reunionGuardada = ctrReunionService.guardarReunion(reunionVO, reunionVO.getUsuCedula(), reunionVO.getLugId());
            return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap() {{put("reunion", reunionGuardada); put("message", "Reunión creada con éxito");}});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>() {{put("message", "Error al crear la reunión: " + e.getMessage());}});
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            ctrReunionService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Reunión eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>() {{
                put("error", "Error al eliminar la reunión: " + e.getMessage());
            }});
        }
    }

    @PutMapping
    public ResponseEntity<Object> actualizarReunion(@RequestBody CtrReunionUpdateVO reunionVO) {
        try {
            CtrReunionDTO reunionActualizada = ctrReunionService.actualizarReunion(reunionVO.getReuId(), reunionVO, reunionVO.getLugId());
            return ResponseEntity.ok().body(new HashMap<String, Object>() {{put("reunion", reunionActualizada); put("message", "Reunión actualizada con éxito");}});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>() {{put("message", "Error al actualizar la reunión: " + e.getMessage());}});
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
    public ResponseEntity<Map<String, String>> EliminarReunionLog(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            ctrReunionService.eliminarReunionLog(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Reunión eliminada con éxito");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al eliminar la reunión: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



}
