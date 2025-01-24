package io.github.devopMarkz.auth_api.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String authority;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public String getAuthority() {
        return authority;
    }

}
