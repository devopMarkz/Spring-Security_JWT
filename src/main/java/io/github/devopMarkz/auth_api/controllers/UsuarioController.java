package io.github.devopMarkz.auth_api.controllers;

import io.github.devopMarkz.auth_api.dtos.UsuarioDTO;
import io.github.devopMarkz.auth_api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements AuxiliarController{

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO usuarioDTO){
        var usuarioCriado = usuarioService.salvar(usuarioDTO);
        URI location = gerarURI(usuarioCriado.id());
        return ResponseEntity.created(location).body(usuarioCriado);
    }

}
