package io.github.devopMarkz.auth_api.controllers.handlers;

import io.github.devopMarkz.auth_api.services.exceptions.UsuarioJaExistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerController {

    @ExceptionHandler(UsuarioJaExistenteException.class)
    public ResponseEntity<String> usuarioJaExistente(UsuarioJaExistenteException e){
        HttpStatus status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(e.getMessage());
    }

}
