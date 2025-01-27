package io.github.devopMarkz.auth_api.controllers;

import io.github.devopMarkz.auth_api.dtos.UsuarioDTO;
import io.github.devopMarkz.auth_api.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements AuxiliarController{

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO usuarioDTO){
        log.info("Usuário salvo no banco de dados: {}", usuarioDTO.nome());
        var usuarioCriado = usuarioService.salvar(usuarioDTO);
        URI location = gerarURI(usuarioCriado.id());
        return ResponseEntity.created(location).body(usuarioCriado);
    }

    @GetMapping
    public String test(){
        return "OK";
    }

}
