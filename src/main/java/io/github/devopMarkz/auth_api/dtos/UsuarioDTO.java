package io.github.devopMarkz.auth_api.dtos;

import io.github.devopMarkz.auth_api.models.Usuario;

public record UsuarioDTO(
        Long id,
        String nome,
        String login,
        String senha
) {

    public static UsuarioDTO convertToDTO(Usuario usuario){
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getSenha());
    }

}
