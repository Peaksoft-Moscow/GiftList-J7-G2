package peaksoft.giftlistj7g2.mapper;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import peaksoft.giftlistj7g2.dto.LoginResponse;
import peaksoft.giftlistj7g2.model.entities.User;
import peaksoft.giftlistj7g2.model.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginMapper {
    Role[] roles;
    @Autowired
    public LoginMapper(Role[] roles) {
        this.roles = roles;
    }

    public LoginResponse mapToResponse(String token, User user) {
        List<String> roles = new ArrayList<>();
        for (Role role : Role.values()) {
            roles.add(role.name());
        }
        return LoginResponse.builder()
                .token(token)
                .roleName(roles)
                .build();
    }
}

