package com.restapi.siscondominio.financiero.persistence.documents;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "pagoServicios")
public class pagoServiciosDocuments {

    @Id
    private String id;
    private String imagenBinarizada;

    public pagoServiciosDocuments(String id, String imagenBinarizada) {
        this.id = id;
        this.imagenBinarizada = imagenBinarizada;
    }

    public pagoServiciosDocuments() {

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagenBinarizada() {
        return imagenBinarizada;
    }

    public void setImagenBinarizada(String imagenBinarizada) {
        this.imagenBinarizada = imagenBinarizada;
    }
}
