package peaksoft.giftlistj7g2.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.giftlistj7g2.model.dto.LoginResponse;
import peaksoft.giftlistj7g2.model.dto.LoginRequest;
import peaksoft.giftlistj7g2.model.service.AuthService;


@RestController
@RequestMapping("/api/auth")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {
    @Autowired
    AuthService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponse> singIn(@RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
}

