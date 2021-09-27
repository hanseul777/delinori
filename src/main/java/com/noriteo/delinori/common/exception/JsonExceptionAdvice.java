package com.noriteo.delinori.common.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

//@RestControllerAdvice
@Log4j2
public class JsonExceptionAdvice {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> exceptionHandler(HttpServletRequest request) {

        log.error("=======================================");
        log.error("=======================================");
        log.error("=======================================");
        log.error("=======================================");
        log.error("=======================================");
        //e.printStackTrace();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("ACCESS ERROR");
    }

}