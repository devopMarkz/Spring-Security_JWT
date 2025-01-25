package io.github.devopMarkz.auth_api.dtos;

import io.github.devopMarkz.auth_api.models.Usuario;

import java.util.List;

public record UsuarioDTO(
        Long id,
        String nome,
        String login,
        String senha,
        List<RoleDTO> roles
) {

    public static UsuarioDTO convertToDTO(Usuario usuario){
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getSenha(), usuario.getRoles().stream().map(role -> new RoleDTO(role.getAuthority())).toList());
    }

}
