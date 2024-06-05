
package com.cursos.api.authorizationserver.exception;

public class ObjectNotFoundException extends RuntimeException{

    //Constructor vacío
    public ObjectNotFoundException() {
    }

    //recibe solo mensaje
    public ObjectNotFoundException(String message) {
        super(message);
    }

    //recibe mensaje y la excepción que causó el error
    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    } 
}
