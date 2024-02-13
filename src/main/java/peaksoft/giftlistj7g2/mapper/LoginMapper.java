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
    Role[] role;
//    @Autowired
//    public LoginMapper(Role[] role) {
//        this.role = role;
//    }

//    public LoginResponse mapToResponse(String token, User user) {
//        List<String> roles = new ArrayList<>();
//        for (Role role : Role.values()) {
//            roles.add(role.name());
//        }
//        return LoginResponse.builder()
//                .token(token)
//                .roleName(roles)
//                .build();
//    }


//    public LoginResponse mapToResponse(String token, User user) {
//        List<String> roles = new ArrayList<>();
//        boolean firstRoleAdded = false;
//        for (Role role : Role.values()) {
//            if (!firstRoleAdded) {
//                roles.add("ADMIN");
//                firstRoleAdded = true;
//            } else {
//                roles.add("USER");
//            }
//        }
//        return LoginResponse.builder()
//                .token(token)
//                .roleName(roles)
//                .build();
//    }
}

