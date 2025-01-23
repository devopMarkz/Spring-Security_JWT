package io.github.devopMarkz.auth_api.repositories;

import io.github.devopMarkz.auth_api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByLogin(String login);
}
