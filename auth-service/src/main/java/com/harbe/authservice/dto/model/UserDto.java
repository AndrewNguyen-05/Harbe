package com.harbe.authservice.dto.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotEmpty(message = "Tên không được bỏ trống")
    @Size(min = 2, message = "Tên phải có độ dài tối thiểu là 2 ký tự")
    private String name;

    @Email
    @NotEmpty(message = "Email không được bỏ trống")
    private String email;

    @NotEmpty(message = "Username không được bỏ trống")
    @Size(min = 3, message = "Username phải có độ dài tối thiểu là 3 ký tự")
    private String username;

    @NotEmpty(message = "Mật khẩu không được bỏ trống")
    @Size(min = 3, message = "Mật khẩu phải có độ dài tối thiểu là 3 ký tự")
    private String password;
}
