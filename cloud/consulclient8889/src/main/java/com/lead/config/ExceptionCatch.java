package com.lead.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * @author Administrator
 */
@RestControllerAdvice
public class ExceptionCatch {


    @ExceptionHandler(value = RuntimeException.class)
    public HashMap<String, Object> executeException(RuntimeException e) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        return map;
    }
}
