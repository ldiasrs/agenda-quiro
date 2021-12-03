package br.com.agendaquiro.controller.auth;

import br.com.agendaquiro.config.PathMappings;
import br.com.agendaquiro.domain.user.User;
import br.com.agendaquiro.domain.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {

    private UserRepository userRepository;

    public AuthorizationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(PathMappings.AUTH_MAPPING)
    public UserResponse login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user =  userRepository.findUserByUsername(authentication.getName());
        return UserResponse.convertToRequestDto(user);
    }
}
