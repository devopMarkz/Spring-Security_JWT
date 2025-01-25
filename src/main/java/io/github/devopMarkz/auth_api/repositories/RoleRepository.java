package io.github.devopMarkz.auth_api.repositories;

import io.github.devopMarkz.auth_api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByAuthority(String authority);

}
