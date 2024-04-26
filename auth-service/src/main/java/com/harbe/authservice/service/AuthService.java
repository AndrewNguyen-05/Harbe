package com.harbe.authservice.service;

import com.harbe.authservice.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
}
