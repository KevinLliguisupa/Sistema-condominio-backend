package com.restapi.siscondominio.control.presentation.controllers;

import com.restapi.siscondominio.control.business.dto.CtrAnuncioDTO;
import com.restapi.siscondominio.control.business.services.CtrAnuncioService;
import com.restapi.siscondominio.control.business.vo.CtrAnuncioQueryVO;
import com.restapi.siscondominio.control.business.vo.CtrAnuncioUpdateVO;
import com.restapi.siscondominio.control.business.vo.CtrAnuncioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Validated
@RestController
@RequestMapping("/control/anuncio")
public class CtrAnuncioController {

    @Autowired
    private CtrAnuncioService ctrAnuncioService;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody CtrAnuncioVO anuncioVO){

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(ctrAnuncioService.guardarAnuncio(anuncioVO, anuncioVO.getUsuCedula(), anuncioVO.getTanId()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el anuncio: "+ e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            ctrAnuncioService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Anuncio eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el anuncio: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Object> actualizarAnuncio( @RequestBody CtrAnuncioUpdateVO anuncioVO) {

        try {

            CtrAnuncioDTO anuncioDTO = ctrAnuncioService.actualizarAnuncio(anuncioVO.getAncId(), anuncioVO, anuncioVO.getTanId());
            return ResponseEntity.ok().body(anuncioDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(ctrAnuncioService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CtrAnuncioDTO>> findAll() {
        return ResponseEntity.ok(ctrAnuncioService.findAll());
    }

    @GetMapping("/ordenDec")
    public ResponseEntity<List<CtrAnuncioDTO>> findAllOrdenDec() {
        return ResponseEntity.ok(ctrAnuncioService.findAllOrderDec());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> eliminarLog(@Valid @NotNull @PathVariable("id") Long id) {
        try {
            ctrAnuncioService.EliminarAnuncioLog(id);
            return ResponseEntity.ok().body(new HashMap() {{put("message", "Anuncio eliminado con éxito");}});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>() {{put("error", "Error al eliminar el anuncio: " + e.getMessage());}});
        }
    }



}
