package com.example.lab5_gtics_20251_20211602.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@RequestMapping("/user")
@SessionAttributes({"numeroSecreto", "intentosRestantes", "historial"})
public class UserController {

    @GetMapping("/juego")
    public String juego(Model model, HttpSession session) {
        if (session.getAttribute("numeroSecreto") == null) {
            session.setAttribute("numeroSecreto", new Random().nextInt(100) + 1);
            session.setAttribute("intentosRestantes", 5);
        }
        model.addAttribute("mensaje", "");
        model.addAttribute("historial", session.getAttribute("historial") == null ? new ArrayList<Partida>() : session.getAttribute("historial"));
        return "juego";
    }

    @PostMapping("/jugar")
    public String jugar(@RequestParam("numero") int numero, Model model, HttpSession session) {
        Integer numeroSecreto = (Integer) session.getAttribute("numeroSecreto");
        Integer intentos = (Integer) session.getAttribute("intentosRestantes");

        if (numero < 1 || numero > 100) {
            model.addAttribute("mensaje", "Número fuera de rango (1 a 100)");
        } else {
            intentos--;
            session.setAttribute("intentosRestantes", intentos);
            if (numero == numeroSecreto) {
                int puntaje = 100 - ((5 - intentos - 1) * 10);
                guardarPartida(session, "Ganó", 5 - intentos - 1, puntaje);
                reiniciarJuego(session);
                model.addAttribute("mensaje", "¡Correcto! Ganaste.");
            } else if (intentos == 0) {
                guardarPartida(session, "Perdió", 5, 0);
                reiniciarJuego(session);
                model.addAttribute("mensaje", "Perdiste. No adivinaste el número.");
            } else {
                model.addAttribute("mensaje", numero < numeroSecreto ? "El número es mayor" : "El número es menor");
            }
        }

        model.addAttribute("historial", session.getAttribute("historial"));
        return "juego";
    }

    private void guardarPartida(HttpSession session, String resultado, int intentos, int puntaje) {
        List<Partida> historial = (List<Partida>) session.getAttribute("historial");
        if (historial == null) historial = new ArrayList<>();
        historial.add(new Partida(LocalDate.now(), resultado, intentos, puntaje));
        session.setAttribute("historial", historial);
    }

    private void reiniciarJuego(HttpSession session) {
        session.setAttribute("numeroSecreto", new Random().nextInt(100) + 1);
        session.setAttribute("intentosRestantes", 5);
    }
}
