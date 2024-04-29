package com.harbe.authservice.service;

import com.harbe.authservice.dto.message.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
}
