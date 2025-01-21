package com.Foro_Hub.Alura.Foro_Hub.Repository;

import com.Foro_Hub.Alura.Foro_Hub.Model.Topico;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>, JpaSpecificationExecutor<Topico>  {
}