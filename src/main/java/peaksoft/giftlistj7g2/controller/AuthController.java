package peaksoft.giftlistj7g2.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import peaksoft.giftlistj7g2.model.dto.AuthRequest;
import peaksoft.giftlistj7g2.model.dto.AuthResponse;
import peaksoft.giftlistj7g2.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> registrationMethod(@RequestBody @Valid AuthRequest authRequest) {
        AuthResponse response = authService.signUp(authRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/with-google")
    public Map<String, Object> withGoggle(OAuth2AuthenticationToken token) throws ClassNotFoundException {
        return authService.signUpWithGoogle(token);
    }
}