package br.com.agendaquiro.controller.auth;

import br.com.agendaquiro.config.PathMappings;
import br.com.agendaquiro.controller.auth.request.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthorizationController {

    @PostMapping(PathMappings.AUTH_MAPPING)
    public String login(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);
        return "TOKEN-FAKE";
    }
}
