package peaksoft.giftlistj7g2.model.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.giftlistj7g2.model.dto.LoginRequest;
import peaksoft.giftlistj7g2.model.dto.LoginResponse;
import peaksoft.giftlistj7g2.model.dto.RegisterRequest;
import peaksoft.giftlistj7g2.model.dto.RegisterResponse;
import peaksoft.giftlistj7g2.model.entities.User;
import peaksoft.giftlistj7g2.model.enums.Role;
import peaksoft.giftlistj7g2.model.repository.AuthRepository;
import peaksoft.giftlistj7g2.security.jwt.JwtUtil;
import peaksoft.giftlistj7g2.model.mapper.LoginMapper;
import peaksoft.giftlistj7g2.model.mapper.UserMapper;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {
    final AuthRepository repository;
    final UserMapper userMapper;
    final PasswordEncoder passwordEncoder;
    final JwtUtil jwtUtil;
    final AuthenticationManager authenticationManager;
    final LoginMapper loginMapper;


    public RegisterResponse save(RegisterRequest request) {
        User user = userMapper.mapToEntity(request);
        if (user.getName().length() < 2 || user.getLastName().length() < 2) {
            throw new RuntimeException("Имя и фамилия юзера должно содержать более двух символов!");
        }
        if (!user.getEmail().contains("@")) {
            throw new RuntimeException("В email должен присутствовать знак @ !");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ADMIN);
//        List<Role> roles = new ArrayList<>();
//        if (repository.findAll().isEmpty()) {
//            roles.add(Role.ADMIN);
//        } else {
//            roles.add(Role.USER);
//        }
        repository.save(user);
        return userMapper.mapToResponse(user);
    }

    public LoginResponse login(LoginRequest request) {
        System.out.println(request.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Not found"));
        String jwt = jwtUtil.generateToken(user);
        return loginMapper.mapToResponse(jwt, user);
    }
}