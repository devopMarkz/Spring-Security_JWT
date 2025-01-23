package io.github.devopMarkz.auth_api.services.exceptions;

public class UsuarioJaExistenteException extends RuntimeException {
    public UsuarioJaExistenteException(String message) {
        super(message);
    }
}
