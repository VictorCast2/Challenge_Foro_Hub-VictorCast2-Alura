package com.Foro_Hub.Alura.Foro_Hub.Controller;

import java.util.*;
import com.Foro_Hub.Alura.Foro_Hub.Model.Dto.ResponsePerfil;
import com.Foro_Hub.Alura.Foro_Hub.Model.Perfil;
import com.Foro_Hub.Alura.Foro_Hub.Services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foro")
@AllArgsConstructor
public class PerfilController {

    private PerfilServices perfilService;
    private UsuarioServices usuarioService;

    /***********************************
     * REST API POST
     * Registrar nuevo Perfil
     * END POINT :
     * http://localhost:8080/foro/perfil
     *************************************/
    @PostMapping("/perfil")
    public ResponseEntity<ResponsePerfil> savePerfil(@RequestBody Perfil perfil){

        String salt = BCrypt.gensalt(12);
        String hashed_password = BCrypt.hashpw(perfil.getUsuario().getContrasena(), salt);
        perfil.getUsuario().setContrasena(hashed_password);

        perfil.setUsuario(usuarioService.saveUsuario(perfil.getUsuario()));
        perfil = perfilService.savePerfil(perfil);
        return new ResponseEntity<ResponsePerfil>(
                new ResponsePerfil(
                        perfil.getNombrecompleto(),
                        perfil.getEmail(),
                        perfil.getUsuario().getNombreusuario()), HttpStatus.CREATED);
    }

    /**************************************
     * REST API GET
     * Obtener todos los perfiles
     * END POINT :
     * http://localhost:8080/foro/perfiles
     ***************************************/
    @GetMapping("/perfiles")
    public List<ResponsePerfil> getAllPerfiles(){

        List<Perfil> perfiles = perfilService.getAllPerfiles();
        List<ResponsePerfil> responsePerfiles = new ArrayList<ResponsePerfil>();
        perfiles.forEach(perfil -> {

            ResponsePerfil responsePerfil =	new ResponsePerfil(
                    perfil.getNombrecompleto(),
                    perfil.getEmail(),
                    perfil.getUsuario().getNombreusuario());

            responsePerfiles.add(responsePerfil);
        });

        return responsePerfiles;
    }

    /*******************************************
     * REST API GET
     * Obtener un Perfil pasando el id
     * END POINT :
     * http://localhost:8080/foro/perfil/1
     ********************************************/
    @GetMapping("/perfil/{id}")
    public ResponseEntity<ResponsePerfil> getPerfilById(@PathVariable("id") long id){
        Perfil  perfil = perfilService.getPerfilById(id);

        return new ResponseEntity<ResponsePerfil>(
                new ResponsePerfil(
                        perfil.getNombrecompleto(),
                        perfil.getEmail(),
                        perfil.getUsuario().getNombreusuario()), HttpStatus.OK);
    }

    /************************************************
     * REST API PUT
     * Actualizar un Perfil por id
     * END POINT :
     * http://localhost:8080/foro/perfil/1
     *************************************************/
    @PutMapping("/perfil/{id}")
    public ResponseEntity<ResponsePerfil> updatePerfil(@PathVariable("id") long id
            ,@RequestBody Perfil perfil){

        perfil = perfilService.updatePerfil(perfil, id);
        return new ResponseEntity<ResponsePerfil>(new ResponsePerfil(
                perfil.getNombrecompleto(),
                perfil.getEmail(),
                perfil.getUsuario().getNombreusuario()), HttpStatus.OK);
    }

    /************************************************
     * REST API DELETE
     * borrar un Perfil por id
     * END POINT :
     * http://localhost:8080/foro/perfil/1
     *************************************************/
    @DeleteMapping("/perfil/{id}")
    public ResponseEntity<String> deletePerfil(@PathVariable("id") long id){

        // delete Perfil from DB
        perfilService.deletePerfil(id);
        return new ResponseEntity<String>("Perfil deleted successfully!.", HttpStatus.OK);
    }

}