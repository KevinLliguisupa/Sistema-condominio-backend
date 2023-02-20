package com.restapi.siscondominio.financiero.business.exceptions;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
