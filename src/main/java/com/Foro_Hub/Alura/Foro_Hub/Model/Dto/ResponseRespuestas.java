package com.Foro_Hub.Alura.Foro_Hub.Model.Dto;

public record ResponseRespuestas(
        Long ID,
        String mensaje,
        Long idtopico,
        boolean solucion,
        ResponsePerfil responsePerfil) {
}