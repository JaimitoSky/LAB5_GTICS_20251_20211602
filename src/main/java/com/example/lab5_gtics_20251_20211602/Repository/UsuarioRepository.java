package com.example.lab5_gtics_20251_20211602.Repository;

import com.example.lab5_gtics_20251_20211602.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);
}