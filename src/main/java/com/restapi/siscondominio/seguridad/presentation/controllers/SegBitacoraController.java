package com.restapi.siscondominio.seguridad.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrAnuncioDTO;
import com.restapi.siscondominio.control.business.dto.CtrLugarDTO;
import com.restapi.siscondominio.control.business.vo.CtrLugarUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrLugarVO;
import com.restapi.siscondominio.seguridad.business.dto.SegBitacoraDTO;
import com.restapi.siscondominio.seguridad.business.services.SegBitacoraService;
import com.restapi.siscondominio.seguridad.business.vo.SegBitacoraQueryVO;
import com.restapi.siscondominio.seguridad.business.vo.SegBitacoraUpdateVO;
import com.restapi.siscondominio.seguridad.business.vo.SegBitacoraVO;
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
@RequestMapping("/seguridad/bitacora")
public class SegBitacoraController {

    @Autowired
    private SegBitacoraService segBitacoraService;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody SegBitacoraVO bitacoraVO) {

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(segBitacoraService.guardarBitacora(bitacoraVO, bitacoraVO.getBitCedIngreso()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el Lugar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Long id) {
        segBitacoraService.delete(id);
    }

    @PutMapping
    public ResponseEntity<Object> actualizarBitacora(@RequestBody SegBitacoraUpdateVO bitacoraUpdateVO) {

        try {
            SegBitacoraDTO segBitacoraDTO = segBitacoraService.actualizarBitacora(bitacoraUpdateVO.getBitId(), bitacoraUpdateVO, bitacoraUpdateVO.getBitCedIngreso());
            return ResponseEntity.ok().body(segBitacoraDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(segBitacoraService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/ordenDec")
    public ResponseEntity<List<SegBitacoraDTO>> findAllOrdenDec() {
        return ResponseEntity.ok(segBitacoraService.findAllOrderDec());
    }

    @GetMapping
    public ResponseEntity<List<SegBitacoraDTO>> findAll() {
        return ResponseEntity.ok(segBitacoraService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> eliminarLog(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            segBitacoraService.EliminarBitacoraLog(id);
            return ResponseEntity.status(HttpStatus.OK).body("Bitacora eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el anuncio: " + e.getMessage());
        }
    }
}
