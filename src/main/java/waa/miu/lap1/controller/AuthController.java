package waa.miu.lap1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.miu.lap1.entity.dto.UserDto;
import waa.miu.lap1.entity.request.LoginRequest;
import waa.miu.lap1.entity.request.RefreshTokenRequest;
import waa.miu.lap1.entity.response.LoginResponse;
import waa.miu.lap1.service.AuthService;
import waa.miu.lap1.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    private final AuthService authService;
    @Autowired
    private UserService userService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public void signup(@RequestBody UserDto userDto) {
        userService.save(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = authService.authenticate(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }
}
