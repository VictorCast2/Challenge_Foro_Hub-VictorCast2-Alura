package com.Foro_Hub.Alura.Foro_Hub.Repository;

import com.Foro_Hub.Alura.Foro_Hub.Model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
}