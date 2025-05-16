package com.example.lab5_gtics_20251_20211602.Controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/adivinanzas")
    public String adivinanzasAdmin() {
        return "adivinanzas";
    }

    @GetMapping("/usuarios")
    public String usuarios() {
        return "usuarios";
    }
}
