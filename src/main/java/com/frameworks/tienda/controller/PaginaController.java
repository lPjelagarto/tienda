package com.frameworks.tienda.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/")
    public String inicio() {
        return "fragments/index";
    }

    @GetMapping("/login")
    public String login() {
        return "fragments/login";
    }

    @GetMapping("/cliente")
    public String cliente() {
        return "fragments/cliente";
    }

    @GetMapping("/vendedor")
    public String vendedor() {
        return "fragments/vendedor";
    }

    @GetMapping("/admin")
    public String admin() {
        return "fragments/admin";
    }

    @GetMapping("/acceso-denegado")
    public String accesoDenegado() {
        return "fragments/acceso-denegado";
    }

    @GetMapping("/redirigir")
    public String redirigir(Authentication authentication) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            return switch (authority.getAuthority()) {
                case "ROLE_ADMIN" -> "redirect:/admin";
                case "ROLE_VENDEDOR" -> "redirect:/vendedor";
                case "ROLE_CLIENTE" -> "redirect:/cliente";
                default -> throw new IllegalStateException("Rol no autorizado");
            };
        }
        return "redirect:/acceso-denegado";
    }
}
