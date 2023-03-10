package com.restapi.siscondominio.control.presentation.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity<>(map,status);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", new ArrayList<>());

        return new ResponseEntity<>(map,status);
    }

    public static ResponseEntity<Object> generateResponse(HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity<>(map,status);
    }

}
