package peaksoft.giftlistj7g2.model.mapper;

import org.springframework.stereotype.Component;
import peaksoft.giftlistj7g2.model.dto.AuthRequest;
import peaksoft.giftlistj7g2.model.dto.AuthResponse;
import peaksoft.giftlistj7g2.model.entities.User;
import peaksoft.giftlistj7g2.model.enums.Role;

import java.time.LocalDate;

@Component
public class AuthMapper {
    public User mapToEntity(AuthRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        if (user.getPassword().equals(request.getRepeatPassword())) {
            user.setPassword(request.getPassword());
        }
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setCreateDate(LocalDate.now());
        return user;
    }

    public AuthResponse mapToResponse(User user) {
        AuthResponse userResponse = new AuthResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(Role.USER);
        return userResponse;
    }
}