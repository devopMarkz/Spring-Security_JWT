package io.github.devopMarkz.auth_api.services.exceptions;

public class UsuarioInexistenteException extends RuntimeException {
    public UsuarioInexistenteException(String message) {
        super(message);
    }
}
