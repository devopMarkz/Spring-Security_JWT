package io.github.devopMarkz.auth_api.services;

import io.github.devopMarkz.auth_api.dtos.AuthDTO;

public interface TokenService {

    public String obterToken(AuthDTO authDTO);

    public String validarTokenJwt(String token);

}
