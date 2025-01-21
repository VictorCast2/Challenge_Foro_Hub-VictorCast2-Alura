package com.Foro_Hub.Alura.Foro_Hub.Repository;

import com.Foro_Hub.Alura.Foro_Hub.Model.Perfil;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface PerfilRepository extends JpaRepository<Perfil, Long>, JpaSpecificationExecutor<Perfil> {
}