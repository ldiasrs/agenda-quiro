package br.com.agendaquiro.controller;

import br.com.agendaquiro.controller.request.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SessionController {

    @PostMapping("login")
    public String login(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);
        return "TOKEN-FAKE";
    }
}
