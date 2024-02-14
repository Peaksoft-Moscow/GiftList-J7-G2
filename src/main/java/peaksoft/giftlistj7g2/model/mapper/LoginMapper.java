package peaksoft.giftlistj7g2.model.mapper;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import peaksoft.giftlistj7g2.model.dto.LoginResponse;
import peaksoft.giftlistj7g2.model.entities.User;
import peaksoft.giftlistj7g2.model.enums.Role;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginMapper {
    Role[] role;

    @Autowired
    public LoginMapper(Role[] role) {
        this.role = role;
    }

    public LoginResponse mapToResponse(String token, User user) {
        return LoginResponse.builder()
                .token(token)
                .role(Role.USER)
                .build();
    }
}

