package com.harbe.authservice.service.impl;

import lombok.AllArgsConstructor;
import com.harbe.authservice.dto.message.RegisterDto;
import com.harbe.authservice.entity.Role;
import com.harbe.authservice.entity.User;
import com.harbe.authservice.repository.RoleRepository;
import com.harbe.authservice.repository.UserRepository;
import com.harbe.authservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.harbe.authservice.exception.HarbeAPIException;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterDto registerDto) {

        // add check for username exists in database
        if(this.userRepository.existsByUsername(registerDto.getUsername())){
            throw new HarbeAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!");
        }

        // add check for email exists in database
        if(this.userRepository.existsByEmail(registerDto.getEmail())){
            throw new HarbeAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = this.roleRepository.findByName("USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User register successfully!";
    }
}
