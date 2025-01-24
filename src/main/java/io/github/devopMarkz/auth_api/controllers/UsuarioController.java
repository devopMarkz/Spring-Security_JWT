package io.github.devopMarkz.auth_api.controllers;

import io.github.devopMarkz.auth_api.dtos.UsuarioDTO;
import io.github.devopMarkz.auth_api.services.AutenticacaoService;
import io.github.devopMarkz.auth_api.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements AuxiliarController{

    private UsuarioService usuarioService;
    private AutenticacaoService autenticacaoService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO usuarioDTO){
        var usuarioCriado = usuarioService.salvar(usuarioDTO);
        URI location = gerarURI(usuarioCriado.id());
        return ResponseEntity.created(location).body(usuarioCriado);
    }

    @GetMapping
    public String test(){
        return "OK";
    }

}
