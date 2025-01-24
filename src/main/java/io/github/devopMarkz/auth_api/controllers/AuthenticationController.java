package io.github.devopMarkz.auth_api.controllers;

import io.github.devopMarkz.auth_api.dtos.AuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String auth(@RequestBody AuthDTO authDTO){

        var usuarioAutenticationToken = new UsernamePasswordAuthenticationToken(authDTO.login(), authDTO.senha());

        authenticationManager.authenticate(usuarioAutenticationToken);

        return "token ....";
    }

}
