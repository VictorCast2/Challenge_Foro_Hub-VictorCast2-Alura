package com.Foro_Hub.Alura.Foro_Hub.Repository;

import com.Foro_Hub.Alura.Foro_Hub.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByNombreusuario(String username);
}