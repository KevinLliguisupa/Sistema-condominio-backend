package com.restapi.siscondominio.financiero.persistence.repositories;
import com.restapi.siscondominio.financiero.persistence.documents.pagoServiciosDocuments;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FinPagoServiciosRespositoryMD extends MongoRepository<pagoServiciosDocuments,String> {
}
