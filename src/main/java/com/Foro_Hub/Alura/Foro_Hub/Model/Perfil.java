package com.Foro_Hub.Alura.Foro_Hub.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table(name = "perfiles")
@Entity(name = "Perfil")
@NoArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombrecompleto;
    private String email;
    private boolean deshabilitado;

    @OneToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public Perfil(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Perfil other = (Perfil) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}