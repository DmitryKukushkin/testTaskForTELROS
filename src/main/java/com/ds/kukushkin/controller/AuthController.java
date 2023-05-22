package com.ds.kukushkin.controller;

import com.ds.kukushkin.dto.LoginDtoResponse;
import com.ds.kukushkin.dto.LoginRequest;
import com.ds.kukushkin.service.AuthService;
import com.ds.kukushkin.service.AuthServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api")
public class AuthController {

    AuthService authService;

    @PostMapping("/auth/login")
    public LoginDtoResponse login(@RequestBody @Validated LoginRequest loginRequest){
        return authService.attemptLogin(loginRequest.getEmail(),loginRequest.getPassword());
    }
}
