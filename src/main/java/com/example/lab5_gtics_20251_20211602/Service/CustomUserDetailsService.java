package com.example.lab5_gtics_20251_20211602.Service;

import com.example.lab5_gtics_20251_20211602.Entity.Usuario;
import com.example.lab5_gtics_20251_20211602.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u = usuarioRepo.findByEmail(email);

        if (u == null || !u.getActivo()) {
            throw new UsernameNotFoundException("Usuario no encontrado o inactivo");
        }

        String role = "ROLE_" + u.getRol().getNombre(); // "ADMIN", "USER"
        return new User(u.getEmail(), u.getPwd(), Collections.singletonList(new SimpleGrantedAuthority(role)));
    }
}