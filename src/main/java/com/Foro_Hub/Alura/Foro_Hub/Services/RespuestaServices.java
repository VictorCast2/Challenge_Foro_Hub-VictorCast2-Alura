package com.Foro_Hub.Alura.Foro_Hub.Services;

import com.Foro_Hub.Alura.Foro_Hub.Exception.ResourceNotFoundException;
import com.Foro_Hub.Alura.Foro_Hub.Model.Respuesta;
import com.Foro_Hub.Alura.Foro_Hub.Repository.RespuestaRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaServices {

    private RespuestaRepository respuestaRepository;


    public Respuesta saveRespuesta(Respuesta respuesta) {
        return respuestaRepository.save(respuesta);
    }

    public List<Respuesta> getAllRespuestas() {
        return respuestaRepository.findAll();
    }

    public Respuesta getRespuestaById(long id) {
        return respuestaRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Respuesta", "Id", id));

    }


    public Respuesta updateRespuesta(Respuesta respuesta, long id) {
        // we need to check whether table with given id is exist in DB or not
        Respuesta existingRespuesta = respuestaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Respuesta", "Id", id));
        existingRespuesta.setPerfil(respuesta.getPerfil());
        existingRespuesta.setMensaje(respuesta.getMensaje());
        // save existing Respuesta to DB
        respuestaRepository.save(existingRespuesta);
        return existingRespuesta;
    }

    public void deleteRespuesta(long id) {
        // check whether a Respuesta exist in a DB or not
        respuestaRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Respuesta", "Id", id));
        respuestaRepository.deleteById(id);
    }

}