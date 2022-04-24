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

    /*
    @ExceptionHandler({MethodArgumentTypeMismatchException.class,
            TypeMismatchException.class, HttpMessageNotReadableException.class, NoSuchElementException.class, InputMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
            HttpClientErrorException.BadRequest ex, WebRequest request) {

        log.error("Error in input format");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message :", "Error in input format");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            BindException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<Map<String, Object>> handleException(BindException e) {

        List<String> errors = new ArrayList<>();
        e.getFieldErrors()
                .forEach(err -> errors.add(err.getField() + ": " + err.getDefaultMessage()));
        e.getGlobalErrors()
                .forEach(err -> errors.add(err.getObjectName() + ": " + err.getDefaultMessage()));

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", errors);

        errorResponse.put("message", e.getLocalizedMessage());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameterException(
            HttpClientErrorException.BadRequest ex, WebRequest request) {

        log.error("Input is empty");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message :", "Input is empty");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }*/

}
