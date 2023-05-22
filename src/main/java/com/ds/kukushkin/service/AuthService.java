package com.ds.kukushkin.service;

import com.ds.kukushkin.dto.LoginDtoResponse;

public interface AuthService {
    LoginDtoResponse attemptLogin(String email, String password);
}
