package com.example.lab5_gtics_20251_20211602.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/redirect")
    public String redirectByRole(Authentication authentication) {
        if (authentication != null) {
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
            if (isAdmin) {
                return "redirect:/admin/adivinanzas";
            } else {
                return "redirect:/user/adivinanzas";
            }
        }
        return "redirect:/login";
    }
}