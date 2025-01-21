package com.Foro_Hub.Alura.Foro_Hub.Model.Dto;

import java.util.List;

public record ResponseTopico(
        Long id,
        String titulo,
        String  mensaje,
        ResponsePerfil responsePerfil,
        List<ResponseRespuestas> respuestas) {
}