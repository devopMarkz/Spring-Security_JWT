package io.github.devopMarkz.auth_api.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.github.devopMarkz.auth_api.dtos.AuthDTO;
import io.github.devopMarkz.auth_api.enums.RoleEnum;
import io.github.devopMarkz.auth_api.models.Usuario;
import io.github.devopMarkz.auth_api.repositories.UsuarioRepository;
import io.github.devopMarkz.auth_api.services.AutenticacaoService;
import io.github.devopMarkz.auth_api.services.exceptions.UsuarioInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
    }

    @Override
    public String obterToken(AuthDTO authDTO) {
        var usuario = usuarioRepository.findByLogin(authDTO.login()).orElseThrow(() -> new UsuarioInexistenteException("Usuário inexistente."));
        return gerarTokenJwt(usuario);
    }

    /**
     *
     * @param token
     * @return Login do usuario caso o token seja valido.
     */
    @Override
    public String validarTokenJwt(String token){
        Algorithm algorithm = Algorithm.HMAC256("my-secret");
        try {
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Erro na validação do token.");
        }
    }

    private String gerarTokenJwt(Usuario usuario){
        List<String> roles = usuario.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getLogin())
                    .withClaim("Roles", roles)
                    .withExpiresAt(gerarDataDeExpiracaoDoToken())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new UsuarioInexistenteException("Erro ao tentar gerar o Token.");
        }
    }

    private Instant gerarDataDeExpiracaoDoToken() {
        return LocalDateTime.now()
                .plusHours(8L)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
