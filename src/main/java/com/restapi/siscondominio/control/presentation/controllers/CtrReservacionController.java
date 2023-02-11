package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrAnuncioDTO;
import com.restapi.siscondominio.control.business.dto.CtrReservacionDTO;
import com.restapi.siscondominio.control.business.services.CtrReservacionService;
import com.restapi.siscondominio.control.business.vo.*;
import com.restapi.siscondominio.control.persistence.entities.CtrReservacion;
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
@RequestMapping("/control/reservacion")
public class CtrReservacionController {

    @Autowired
    private CtrReservacionService ctrReservacionService;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody CtrReservacionVO reservacionVO){

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(ctrReservacionService.guardarReservacion(reservacionVO, reservacionVO.getUsuCedula(), reservacionVO.getLugId()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la reservación: "+ e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            ctrReservacionService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Reservación eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la reservación: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @NotNull @PathVariable("id") Long id) {
        try{
            ctrReservacionService.actualizarReserEstado(id);
            return ResponseEntity.status(HttpStatus.OK).body("Reservación Aprobada");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al aprobar la reservación: " + e.getMessage());
        }

    }

    @PutMapping
    public ResponseEntity<Object> actualizarReservacion( @RequestBody CtrReservacionUpdateVO reservacionUpdateVO) {

        try {

            CtrReservacionDTO reservacionDTO = ctrReservacionService.actualizarReservacion(reservacionUpdateVO.getResId(), reservacionUpdateVO, reservacionUpdateVO.getLugId());
            return ResponseEntity.ok().body(reservacionDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(ctrReservacionService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CtrReservacionDTO>> findAll() {
        return ResponseEntity.ok(ctrReservacionService.findAll());
    }

    @GetMapping("/masrecientes")
    public ResponseEntity<List<CtrReservacionDTO>> listarReservacionesMasRecientes() {
        List<CtrReservacionDTO> reservaciones = ctrReservacionService.listarReservacionesFechaReciente();
        return ResponseEntity.ok(reservaciones);
    }

    @PutMapping("/estado/{id}")
    public ResponseEntity<?> EliminarReservacionLog(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            ctrReservacionService.EliminarReserEstado(id);
            return ResponseEntity.status(HttpStatus.OK).body("Reservación eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la reservación: " + e.getMessage());
        }
    }




}
