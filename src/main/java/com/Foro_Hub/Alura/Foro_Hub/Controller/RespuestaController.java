package com.Foro_Hub.Alura.Foro_Hub.Controller;

import java.util.*;
import com.Foro_Hub.Alura.Foro_Hub.Model.Dto.*;
import com.Foro_Hub.Alura.Foro_Hub.Model.*;
import com.Foro_Hub.Alura.Foro_Hub.Services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/foro")
@AllArgsConstructor
public class RespuestaController {

    private PerfilServices perfilService;
    private RespuestaServices respuestaService;
    private TopicoServices topicoService;

    /*************************************************
     * REST API POST
     * Registrar nueva Respuesta
     * END POINT :
     * http://localhost:8080/foro//topico/1/respuesta
     **************************************************/
    @PostMapping("/topico/{idtopico}/respuesta")
    public ResponseEntity<ResponseTopico> saveRespuesta(@RequestBody Respuesta respuesta, @PathVariable("idtopico") long idtopico){
        respuesta.setIdtopico(idtopico);
        respuesta.setPerfil(perfilService.getPerfilById(respuesta.getPerfil().getId()));

        respuesta = respuestaService.saveRespuesta(respuesta);

        Topico topico = topicoService.getTopicoById(idtopico);
        ResponsePerfil perfil = new ResponsePerfil(
                topico.getPerfil().getNombrecompleto(),
                topico.getPerfil().getEmail(),
                topico.getPerfil().getUsuario().getNombreusuario());

        List<ResponseRespuestas> responseRespuestas = new ArrayList<ResponseRespuestas>();

        topico.getRespuestas().forEach(respuestaTopico -> {

            responseRespuestas.add(new ResponseRespuestas(
                    respuestaTopico.getId(),
                    respuestaTopico.getMensaje(),
                    respuestaTopico.getIdtopico(),
                    respuestaTopico.getSolucion(), new ResponsePerfil(
                    respuestaTopico.getPerfil().getNombrecompleto(),
                    respuestaTopico.getPerfil().getEmail(),
                    respuestaTopico.getPerfil().getUsuario().getNombreusuario())));
        });
        ResponseTopico responseTopico = new ResponseTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                perfil, responseRespuestas);
        return new ResponseEntity<ResponseTopico>(responseTopico, HttpStatus.CREATED);
    }


    /**************************************
     * REST API GET
     * Obtener todas las Respuestas
     * END POINT :
     * http://localhost:8080/foro/respuestas
     ***************************************/

    /*******************************************
     * REST API GET
     * Obtener una Respuesta pasando el id
     * END POINT :
     * http://localhost:8080/foro/respuesta/1
     ********************************************/
    @GetMapping("/respuesta/{id}")
    public ResponseEntity<Respuesta> getRespuestaById(@PathVariable("id") long id){
        return new ResponseEntity<Respuesta>(respuestaService.getRespuestaById(id), HttpStatus.OK);
    }

    /************************************************
     * REST API PUT
     * Actualizar una Respuesta pasando el id
     * END POINT :
     * http://localhost:8080/foro/perfil/5/respuesta/2
     *************************************************/
    @PutMapping("/respuesta/{resp_id}")
    public ResponseEntity<ResponseRespuestas> updateRespuesta(@PathVariable("resp_id") long resp_id
            ,@RequestBody Respuesta respuesta){

        Respuesta existenteRespuesta = respuestaService.getRespuestaById(resp_id);

        ResponseRespuestas responseRespuestas;

        var vHttpStatus = HttpStatus.NOT_MODIFIED;
        if(existenteRespuesta.getPerfil().getId() == respuesta.getPerfil().getId()) {
            respuesta = respuestaService.updateRespuesta(respuesta, resp_id);

            existenteRespuesta.setMensaje(respuesta.getMensaje());
            vHttpStatus = HttpStatus.OK;

        }
        respuesta.setPerfil(perfilService.getPerfilById(respuesta.getPerfil().getId()));

        responseRespuestas = new ResponseRespuestas(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getIdtopico(),
                respuesta.getSolucion(), new ResponsePerfil(
                respuesta.getPerfil().getNombrecompleto(),
                respuesta.getPerfil().getEmail(),
                respuesta.getPerfil().getUsuario().getNombreusuario()));
        System.out.println(existenteRespuesta);
        return new ResponseEntity<ResponseRespuestas>(responseRespuestas, vHttpStatus);
    }

    /************************************************
     * REST API DELETE
     * borrar una Respuesta pasando el id
     * END POINT :
     * http://localhost:8080/foro/respuesta/1
     *************************************************/
    @DeleteMapping("/respuesta/{id}")
    public ResponseEntity<String> deleteRespuesta(@PathVariable("id") long id){
        // delete respuesta from DB
        respuestaService.deleteRespuesta(id);
        return new ResponseEntity<String>("Respuesta deleted successfully!.", HttpStatus.OK);
    }

}