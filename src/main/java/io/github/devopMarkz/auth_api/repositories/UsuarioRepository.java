package io.github.devopMarkz.auth_api.repositories;

import io.github.devopMarkz.auth_api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByLogin(String login);

    @Query("SELECT obj FROM Usuario obj JOIN FETCH obj.roles WHERE obj.login = :login")
    Optional<Usuario> findByLogin(String login);

}
