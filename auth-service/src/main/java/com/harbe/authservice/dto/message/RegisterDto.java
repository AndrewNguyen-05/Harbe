package com.harbe.authservice.dto.message;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, message = "Name must contain at least 3 characters")
    private String name;

    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @NotEmpty(message = "Username can not be empty")
    private String username;

    @Size(min = 3, message = "Password must contain at least 3 characters")
    @NotEmpty(message = "Password can not be empty")
    private String password;

    @NotEmpty(message = "Số điện thoại không được bỏ trống")
    @Size(min = 9, message = "Số điện thoại phải có độ dài tối thiểu là 9 ký tự")
    private String phone;

    private String gender;
    private Date dateOfBirth;
}
