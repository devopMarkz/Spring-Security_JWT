package io.github.devopMarkz.auth_api.services.impl;

import io.github.devopMarkz.auth_api.dtos.RoleDTO;
import io.github.devopMarkz.auth_api.dtos.UsuarioDTO;
import io.github.devopMarkz.auth_api.models.Role;
import io.github.devopMarkz.auth_api.models.Usuario;
import io.github.devopMarkz.auth_api.repositories.RoleRepository;
import io.github.devopMarkz.auth_api.repositories.UsuarioRepository;
import io.github.devopMarkz.auth_api.services.UsuarioService;
import io.github.devopMarkz.auth_api.services.exceptions.UsuarioJaExistenteException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        verificaSeUsuarioJaExiste(usuarioDTO);

        var usuario = converteDtoParaUsuario(usuarioDTO);

        var novoUsuario = usuarioRepository.save(usuario);

        return UsuarioDTO.convertToDTO(novoUsuario);
    }

    private Usuario converteDtoParaUsuario(UsuarioDTO usuarioDTO) {
        var usuario = new Usuario();

        if ((usuarioDTO.id() != null)) {
            usuario.setId(usuario.getId());
        }

        List<Role> roles = usuarioDTO.roles().stream().map(roleDTO -> roleRepository.findByAuthority(roleDTO.authority())).toList();

        usuario.setNome(usuarioDTO.nome());
        usuario.setLogin(usuarioDTO.login());
        usuario.setRoles(roles);
        
        var senhaEncodada = passwordEncoder.encode(usuarioDTO.senha());
        usuario.setSenha(senhaEncodada);

        return usuario;
    }

    private String encodarSenhaDoUsuario(String senha){
        return passwordEncoder.encode(senha);
    }

    private void verificaSeUsuarioJaExiste(UsuarioDTO usuarioDTO) {
        if(usuarioRepository.existsByLogin(usuarioDTO.login())){
            throw new UsuarioJaExistenteException("Usuário já existente no sistema.");
        }
    }

}
