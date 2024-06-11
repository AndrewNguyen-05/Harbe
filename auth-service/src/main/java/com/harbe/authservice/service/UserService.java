package com.harbe.authservice.service;

import com.harbe.authservice.dto.model.UserDto;
import com.harbe.authservice.dto.response.ObjectResponse;

public interface UserService {
    UserDto createUser(UserDto userDto);
    ObjectResponse<UserDto> getAllUser(int pageSize, int pageNo, String sortBy, String sortDir);
    UserDto getUserById(Long id);
    UserDto getUserByUsername(String userName);
    UserDto updateUser(UserDto userDto, Long userId);
    void deleteUser(Long userId);
    UserDto getUserProfile(Long userId);
    ObjectResponse<UserDto> searchUser(String name, int pageNo, int pageSize, String sortBy, String sortDir);
}
