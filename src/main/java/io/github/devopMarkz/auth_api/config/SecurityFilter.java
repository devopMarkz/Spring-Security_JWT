package io.github.devopMarkz.auth_api.config;

import io.github.devopMarkz.auth_api.models.Usuario;
import io.github.devopMarkz.auth_api.repositories.UsuarioRepository;
import io.github.devopMarkz.auth_api.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = extraiTokenHeader(request);

        if(token != null){
            String login = tokenService.validarTokenJwt(token);
            Usuario usuario = usuarioRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String extraiTokenHeader(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");

        if(authHeader == null){
            return null;
        }

        // Bearer <Token>
        if(!authHeader.split(" ")[0].equals("Bearer")){
            return null;
        }

        return authHeader.split(" ")[1];
    }

}
