package com.restapi.siscondominio.financiero.persistence.documents;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
@Document(collection = "pagoIncidencias")
public class pagoIncidenciasDocuments {
    @Id
    private String id;
    private String imagenBinarizada;

    public pagoIncidenciasDocuments(String id, String imagenBinarizada) {
        this.id = id;
        this.imagenBinarizada = imagenBinarizada;
    }

    public pagoIncidenciasDocuments() {

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
