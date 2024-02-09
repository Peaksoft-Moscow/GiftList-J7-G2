package peaksoft.giftlistj7g2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import peaksoft.giftlistj7g2.model.entities.User;
import peaksoft.giftlistj7g2.model.enums.Role;
import peaksoft.giftlistj7g2.model.mapper.AuthMapper;
import peaksoft.giftlistj7g2.repository.AuthRepository;
import peaksoft.giftlistj7g2.model.dto.AuthRequest;
import peaksoft.giftlistj7g2.model.dto.AuthResponse;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Validated
public class AuthService {
    private final AuthMapper authMapper;
    private final AuthRepository authRepository;

    public AuthResponse signUp(AuthRequest authRequest) {
        User user = authMapper.mapToEntity(authRequest);
        user.setCreateDate(LocalDate.now());
        user.setRole(Role.USER);
        authRepository.save(user);
        validationRequest(user);
        return authMapper.mapToResponse(user);
    }

    public void validationRequest(User user) {
        try {
            if (!authRepository.findByEmail(user.getEmail()).isEmpty()) {
                log.error("This email is not empty ");
                throw new RuntimeException(" A user with this email already exists");
            }
            if (!user.getEmail().contains("@")) {
                log.error("Email doesn't have symbol @ ");
                throw new RuntimeException("Uncorrect email! ");
            }
            if (user.getPassword().length() < 8) {
                log.error("Password must be at least 8 characters");
                throw new RuntimeException("password must be at least 8 characters");
            }

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    public Map<String, Object> signUpWithGoogle(OAuth2AuthenticationToken token) throws ClassNotFoundException {
        OAuth2AuthenticatedPrincipal principal = token.getPrincipal();
        if (token == null) {
            throw new ClassNotFoundException("Token is empty");
        }
        Map<String, Object> map = principal.getAttributes();
        System.out.println(map.get("family_name"));
        User user = new User();
        user.setName((String) (map.get("given_name")));
        user.setLastName((String) map.get("family_name"));
        user.setEmail((String) map.get("email"));
        user.setPassword((String) map.get("given_name"));
        map.get("given_name");
        user.setCreateDate(LocalDate.now());
        user.setRole(Role.USER);
        authRepository.save(user);
        Map<String, Object> getValues = new HashMap<>();
        getValues.put("name: ", user.getName());
        getValues.put("lastname: ", user.getLastName());
        getValues.put("email: ", user.getEmail());
        getValues.put("role: ", user.getRole());
        getValues.put("createdate", LocalDate.now());
        return getValues;
    }
}
