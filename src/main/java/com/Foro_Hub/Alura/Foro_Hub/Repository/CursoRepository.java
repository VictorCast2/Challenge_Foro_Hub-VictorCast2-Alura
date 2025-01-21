package com.Foro_Hub.Alura.Foro_Hub.Repository;

import com.Foro_Hub.Alura.Foro_Hub.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {


}