package com.softwareacademy.webapp.baroni_julien_p5.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Composant permettant de gérer les exceptions soulevés par les services appelés,
 * au moment où elles remontent la stack jusqu'au controleur
 * <p>
 */

@Slf4j
@Component
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(
            NoDataFoundException ex, WebRequest request) {

        log.error("There isn't any output in the Database to match the request");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp :", LocalDateTime.now());
        body.put("message :", "There isn't any output in the Database to match the request");

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
