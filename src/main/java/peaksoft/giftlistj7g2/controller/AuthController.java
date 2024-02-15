package peaksoft.giftlistj7g2.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.giftlistj7g2.model.dto.AuthRequest;
import peaksoft.giftlistj7g2.model.dto.AuthResponse;
import peaksoft.giftlistj7g2.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthController {
    AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> registrationMethod(@RequestBody @Valid AuthRequest authRequest) {
        AuthResponse response = authService.signUp(authRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/with-google")
    public Map<String, Object> withGoggle(OAuth2AuthenticationToken token) throws ClassNotFoundException {
        return authService.signUpWithGoogle(token);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponse> singIn(@RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
}