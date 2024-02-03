package peaksoft.giftlistj7g2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import peaksoft.giftlistj7g2.model.entities.User;
import peaksoft.giftlistj7g2.model.mapper.AuthMapper;
import peaksoft.giftlistj7g2.repository.AuthRepository;
import peaksoft.giftlistj7g2.model.dto.AuthRequest;
import peaksoft.giftlistj7g2.model.dto.AuthResponse;

@Service
@Slf4j
@RequiredArgsConstructor
@Validated
public class AuthService {
    private final AuthMapper authMapper;
    private final AuthRepository authRepository;
    public AuthResponse signUp(AuthRequest authRequest) {
        User user = authMapper.mapToEntity(authRequest);
        authRepository.save(user);
        validationRequest(user);
        return authMapper.mapToResponse(user);
    }



    public void validationRequest(User user) {
        try {
            if (!authRepository.findByEmail(user.getEmail()).isEmpty()){
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
            if (user.getPassword().length() != user.getRepeatPassword().length()){
                log.error("Password mismatch");
            }

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }


}
