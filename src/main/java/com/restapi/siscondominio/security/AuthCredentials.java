package com.restapi.siscondominio.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String usuCedula;
    private String usuClave;
}
