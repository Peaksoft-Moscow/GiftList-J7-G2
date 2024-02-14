package peaksoft.giftlistj7g2.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.giftlistj7g2.dto.LoginResponse;
import peaksoft.giftlistj7g2.dto.RegisterRequest;
import peaksoft.giftlistj7g2.dto.RegisterResponse;
import peaksoft.giftlistj7g2.dto.LoginRequest;
import peaksoft.giftlistj7g2.mapper.LoginMapper;
import peaksoft.giftlistj7g2.service.UserService;


@RestController
@RequestMapping("/api/auth")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<RegisterResponse> registration(@RequestBody RegisterRequest request) {
        RegisterResponse response = userService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponse> singIn(@RequestBody LoginRequest request) {
        System.out.println("Log in controller");
        LoginResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
}

