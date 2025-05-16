package com.example.lab5_gtics_20251_20211602.Controller;
import com.example.lab5_gtics_20251_20211602.Entity.Rol;
import com.example.lab5_gtics_20251_20211602.Entity.Usuario;
import com.example.lab5_gtics_20251_20211602.Repository.UsuarioRepository;
import com.example.lab5_gtics_20251_20211602.Repository.RolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;

    @GetMapping("/adivinanzas")
    public String adivinanzasAdmin() {
        return "adivinanzas";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        return "usuarios";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario_form";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuario.setActivo(true);
        Rol rolUser = rolRepository.findByNombre("USER");
        usuario.setRol(rolUser);
        usuarioRepository.save(usuario);
        return "redirect:/admin/usuarios";
    }
}
