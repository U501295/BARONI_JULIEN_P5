package com.softwareacademy.webapp.baroni_julien_p5.controller.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Exception personnalisée générale à tous les services métiers pour le cas où
 * l'input respecte le formalisme imposé mais qu'il ne renvoit rien
 * <p>
 */

@Slf4j
public class NoDataFoundException extends RuntimeException {

    public NoDataFoundException() {

        super("No data found");
        //log.error("There isn't any output in the Database to match the request");
    }
}
