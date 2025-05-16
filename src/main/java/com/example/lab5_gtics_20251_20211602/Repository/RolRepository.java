package com.example.lab5_gtics_20251_20211602.Repository;

import com.example.lab5_gtics_20251_20211602.Entity.Rol;
import com.example.lab5_gtics_20251_20211602.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNombre(String nombre);
}
