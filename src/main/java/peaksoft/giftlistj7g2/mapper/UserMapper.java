package peaksoft.giftlistj7g2.mapper;

import org.springframework.stereotype.Component;
import peaksoft.giftlistj7g2.dto.RegisterRequest;
import peaksoft.giftlistj7g2.dto.RegisterResponse;
import peaksoft.giftlistj7g2.dto.UserResponse;
import peaksoft.giftlistj7g2.model.entities.User;

import java.time.LocalDate;

@Component
public class UserMapper {

    public User mapToEntity(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        user.setCreateDate(LocalDate.now());
        return user;
    }

    public RegisterResponse mapToResponse(User user) {
        return RegisterResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .lastName(user.getLastName())
                .age(user.getAge())
                .response("Success Registered").build();
    }

    public UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastName())
                .email(user.getEmail())
                .age(user.getAge())
                .createDate(user.getCreateDate()).build();
    }
}