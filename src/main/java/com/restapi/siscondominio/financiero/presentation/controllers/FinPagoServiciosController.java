package com.restapi.siscondominio.financiero.presentation.controllers;

import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.restapi.siscondominio.financiero.business.dto.FinPagoServiciosDTO;
import com.restapi.siscondominio.financiero.business.services.FinPagoServiciosService;
import com.restapi.siscondominio.financiero.business.vo.FinPagoServiciosUpdateVO;
import com.restapi.siscondominio.financiero.business.vo.FinPagoServiciosVO;
import com.restapi.siscondominio.financiero.business.vo.FinTipoServicioVO;
import com.restapi.siscondominio.financiero.persistence.documents.pagoServiciosDocuments;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.multipart.MultipartFile;

//

@Validated
@RestController
@RequestMapping("/financiero/pagoservicios")
public class FinPagoServiciosController {

    @Autowired
    private FinPagoServiciosService finPagoServiciosService;

    @PostMapping
    public String save(@Valid @RequestBody FinPagoServiciosVO vO)  {
        return finPagoServiciosService.save(vO).toString();
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody FinPagoServiciosUpdateVO vO) {
        finPagoServiciosService.updateImagen(id, vO);
    }

    @GetMapping("/{id}")
    public FinPagoServiciosDTO getById(@Valid @NotNull @PathVariable("id") Long id) {
        return finPagoServiciosService.getById(id);
    }

    @GetMapping("/imagenrecibo")
    public List<pagoServiciosDocuments> getAllImagenesPagos() {
        return finPagoServiciosService.getAllImagenesPagos();
    }


    @GetMapping
    public List<FinPagoServiciosDTO> query() {
        return finPagoServiciosService.query();
    }
}
