package io.github.devopMarkz.auth_api.services;

import io.github.devopMarkz.auth_api.dtos.AuthDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AutenticacaoService extends UserDetailsService {

    public String obterToken(AuthDTO authDTO);

}
