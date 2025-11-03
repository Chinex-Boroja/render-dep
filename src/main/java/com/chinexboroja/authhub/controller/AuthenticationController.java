package com.chinexboroja.authhub.controller;

import com.chinexboroja.authhub.dtos.LoginResponse;
import com.chinexboroja.authhub.dtos.LoginUserDto;
import com.chinexboroja.authhub.dtos.RegisterUserDto;
import com.chinexboroja.authhub.model.User;
import com.chinexboroja.authhub.service.AuthenticationService;
import com.chinexboroja.authhub.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signUp(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
        User user = authenticationService.login(loginUserDto);

        String token = jwtService.generateToken(user);
        long jwtExpirationTime = jwtService.getJwtExpirationTime();

        LoginResponse loginResponse = new LoginResponse(token, jwtExpirationTime);

        return ResponseEntity.ok(loginResponse);
    }
}
