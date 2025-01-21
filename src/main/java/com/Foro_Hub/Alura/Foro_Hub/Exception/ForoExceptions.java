package com.Foro_Hub.Alura.Foro_Hub.Exception;

import org.springframework.http.HttpStatus;
import lombok.*;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
public class ForoExceptions extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public ForoExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;

    }

}