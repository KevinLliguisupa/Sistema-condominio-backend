package com.restapi.siscondominio.financiero.persistence.repositories;
import com.restapi.siscondominio.financiero.persistence.documents.pagoIncidenciasDocuments;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface FinPagoIncidenciasRespositoryMD extends MongoRepository<pagoIncidenciasDocuments,String> {
}
