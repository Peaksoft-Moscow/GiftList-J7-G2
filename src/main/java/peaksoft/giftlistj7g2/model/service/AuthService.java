package peaksoft.giftlistj7g2.model.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import peaksoft.giftlistj7g2.model.dto.LoginRequest;
import peaksoft.giftlistj7g2.model.dto.LoginResponse;
import peaksoft.giftlistj7g2.model.entities.User;
import peaksoft.giftlistj7g2.model.repository.AuthRepository;
import peaksoft.giftlistj7g2.security.jwt.JwtUtil;
import peaksoft.giftlistj7g2.model.mapper.LoginMapper;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    AuthRepository repository;
    JwtUtil jwtUtil;
    AuthenticationManager authenticationManager;
    LoginMapper loginMapper;


    public LoginResponse login(LoginRequest request) {
        System.out.println(request.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Not found"));
        String jwt = jwtUtil.generateToken(user);
        return loginMapper.mapToResponse(jwt, user);
    }
}