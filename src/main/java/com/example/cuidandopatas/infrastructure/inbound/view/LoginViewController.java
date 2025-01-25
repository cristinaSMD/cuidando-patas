package com.example.cuidandopatas.infrastructure.inbound.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Esto buscará el archivo login.html en src/main/resources/templates/
    }
}
