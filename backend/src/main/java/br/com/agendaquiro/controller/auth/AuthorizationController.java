package br.com.agendaquiro.controller.auth;

import br.com.agendaquiro.config.PathMappings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {
    @GetMapping(PathMappings.AUTH_MAPPING)
    public String login() {
        return "JWT Created on header";
    }
}
