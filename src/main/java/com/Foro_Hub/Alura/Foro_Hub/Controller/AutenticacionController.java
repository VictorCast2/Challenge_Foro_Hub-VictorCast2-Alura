package com.Foro_Hub.Alura.Foro_Hub.Controller;

import com.Foro_Hub.Alura.Foro_Hub.Config.Services.TokenServices;
import com.Foro_Hub.Alura.Foro_Hub.Model.Dto.*;
import com.Foro_Hub.Alura.Foro_Hub.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/foro")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServices tokenService;

    @PostMapping("/login")
    public ResponseEntity atenticarusuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.nombreusuario(), datosAutenticacionUsuario.contrasena());
        var usuarioAutentidado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutentidado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

}