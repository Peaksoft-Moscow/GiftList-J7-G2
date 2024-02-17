package peaksoft.giftlistj7g2.model.mapper;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import peaksoft.giftlistj7g2.model.dto.LoginResponse;
import peaksoft.giftlistj7g2.model.entities.User;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginMapper {

    public LoginResponse mapToResponse(String token, User user) {
        return LoginResponse.builder()
                .token(token)
                .role(user.getRole())
                .build();
    }
}

