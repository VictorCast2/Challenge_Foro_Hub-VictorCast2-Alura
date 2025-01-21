package com.Foro_Hub.Alura.Foro_Hub.Services;

import com.Foro_Hub.Alura.Foro_Hub.Exception.ResourceNotFoundException;
import com.Foro_Hub.Alura.Foro_Hub.Model.Curso;
import com.Foro_Hub.Alura.Foro_Hub.Repository.CursoRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CursoServices {


    private CursoRepository cursoRepository;


    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Curso getCursoById(long id) {
        return cursoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Curso", "Id", id));
    }

    public Curso updateCurso(Curso curso, long id) {
        // we need to check whether curso with given id is exist in DB or not
        Curso existingCurso = cursoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Curso", "Id", id));
        existingCurso.setNombre(curso.getNombre());
        existingCurso.setCategoria(curso.getCategoria());
        // save existing curso to DB
        cursoRepository.save(existingCurso);
        return existingCurso;
    }

    public void deleteCurso(long id) {
        // check whether a employee exist in a DB or not
        cursoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Curso", "Id", id));
        cursoRepository.deleteById(id);
    }
}