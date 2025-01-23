package io.github.devopMarkz.auth_api.services.impl;

import io.github.devopMarkz.auth_api.dtos.UsuarioDTO;
import io.github.devopMarkz.auth_api.models.Usuario;
import io.github.devopMarkz.auth_api.repositories.UsuarioRepository;
import io.github.devopMarkz.auth_api.services.UsuarioService;
import io.github.devopMarkz.auth_api.services.exceptions.UsuarioJaExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        verificaSeUsuarioJaExiste(usuarioDTO);

            var usuario = new Usuario();
        usuario.setNome(usuarioDTO.nome());
        usuario.setLogin(usuarioDTO.login());
        usuario.setSenha(usuarioDTO.senha());

        var novoUsuario = usuarioRepository.save(usuario);

        return UsuarioDTO.convertToDTO(novoUsuario);
    }

    private void verificaSeUsuarioJaExiste(UsuarioDTO usuarioDTO) {
        if(usuarioRepository.existsByLogin(usuarioDTO.login())){
            throw new UsuarioJaExistenteException("Usuário já existente no sistema.");
        }
    }

}
