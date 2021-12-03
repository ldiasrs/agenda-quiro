package br.com.agendaquiro.controller.auth;

import br.com.agendaquiro.domain.user.User;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    private String tenant;
    private String username;
    private String password;
    private Boolean enabled;

    public static UserResponse convertToRequestDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserResponse.class);
    }
}
