package peaksoft.giftlistj7g2.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import peaksoft.giftlistj7g2.model.dto.AuthResponse;
import peaksoft.giftlistj7g2.model.dto.LoginRequest;
import peaksoft.giftlistj7g2.model.dto.LoginResponse;
import peaksoft.giftlistj7g2.model.entities.User;
import peaksoft.giftlistj7g2.model.enums.Role;
import peaksoft.giftlistj7g2.model.mapper.AuthMapper;
import peaksoft.giftlistj7g2.model.mapper.LoginMapper;
import peaksoft.giftlistj7g2.repository.UserRepository;
import peaksoft.giftlistj7g2.model.dto.AuthRequest;
import peaksoft.giftlistj7g2.security.jwt.JwtUtil;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
@Service
@Slf4j
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    AuthMapper authMapper;
    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;
    JwtUtil jwtUtil;
    LoginMapper loginMapper;

    public AuthResponse signUp(AuthRequest authRequest) {
        User user = authMapper.mapToEntity(authRequest);
        userRepository.save(user);
        return authMapper.mapToResponse(user);
    }

    public Map<String, Object> signUpWithGoogle(OAuth2AuthenticationToken token) throws ClassNotFoundException {
        OAuth2AuthenticatedPrincipal principal = token.getPrincipal();
        if (token == null) {
            throw new ClassNotFoundException("Token is empty");
        }
        Map<String, Object> map = principal.getAttributes();
        User user = new User();
        user.setName((String) (map.get("given_name")));
        user.setLastName((String) map.get("family_name"));
        user.setEmail((String) map.get("email"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        map.get("given_name");
        user.setCreateDate(LocalDate.now());
        user.setRole(Role.USER);
        userRepository.save(user);
        Map<String, Object> getValues = new HashMap<>();
        getValues.put("name: ", user.getName());
        getValues.put("lastname: ", user.getLastName());
        getValues.put("email: ", user.getEmail());
        getValues.put("role: ", user.getRole());
        getValues.put("createDate", LocalDate.now());
        return getValues;
    }

    public LoginResponse login(LoginRequest request) {
        System.out.println(request.getEmail());
        User user = userRepository.findByEmail(request.getEmail()).get();
        if (user != null) {
            String jwt = jwtUtil.generateToken(user);
            return loginMapper.mapToResponse(jwt, user);
        } else {
            throw new RuntimeException("Invalid email");
        }
    }
}