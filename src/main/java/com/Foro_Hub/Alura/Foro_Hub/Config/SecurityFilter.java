package com.Foro_Hub.Alura.Foro_Hub.Config;

import java.io.IOException;
import com.Foro_Hub.Alura.Foro_Hub.Config.Services.TokenServices;
import com.Foro_Hub.Alura.Foro_Hub.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    private TokenServices tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");
        if (authHeader!= null)
        {
            var token = authHeader.replace("Bearer", "").trim();

            var subject = tokenService.getSubject(token);
            if(subject != null)
            {
                var usuario = usuarioRepository.findByNombreusuario(subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
