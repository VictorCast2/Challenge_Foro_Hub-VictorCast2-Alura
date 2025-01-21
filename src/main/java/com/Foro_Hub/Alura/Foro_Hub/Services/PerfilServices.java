package com.Foro_Hub.Alura.Foro_Hub.Services;

import com.Foro_Hub.Alura.Foro_Hub.Exception.ResourceNotFoundException;
import com.Foro_Hub.Alura.Foro_Hub.Model.*;
import com.Foro_Hub.Alura.Foro_Hub.Repository.*;
import lombok.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PerfilServices {
    private UsuarioRepository usuarioRepository;
    private PerfilRepository perfilRepository;

    public Perfil savePerfil(Perfil perfil) {
        Usuario usuario = usuarioRepository.save(perfil.getUsuario());
        perfil.setUsuario(usuario);
        return perfilRepository.save(perfil);
    }

    public List<Perfil> getAllPerfiles() {
        return perfilRepository.findAll();
    }

    public Perfil getPerfilById(long id) {
        return perfilRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Perfil", "Id", id));
    }

    public Perfil updatePerfil(Perfil perfil, long id) {
        // we need to check whether Perfil with given id is exist in DB or not
        Perfil existingPerfil = perfilRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Perfil", "Id", id));
        existingPerfil.setNombrecompleto(perfil.getNombrecompleto());
        existingPerfil.setEmail(perfil.getEmail());
        // save existing Perfil to DB
        perfilRepository.save(existingPerfil);
        return existingPerfil;
    }

    public void deletePerfil(long id) {
        // check whether a Perfil exist in a DB or not
        perfilRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Perfil", "Id", id));
        perfilRepository.deleteById(id);
    }

}