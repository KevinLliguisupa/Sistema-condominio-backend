package com.restapi.siscondominio.control.business.exeption;

public class DuplicatedException extends RuntimeException {

    public DuplicatedException(String message){
        super(message);
    }
}
