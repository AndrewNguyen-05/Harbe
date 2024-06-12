package com.harbe.authservice.dto.model;

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

    @NotEmpty(message = "Số điện thoại không được bỏ trống")
    @Size(min = 9, message = "Số điện thoại phải có độ dài tối thiểu là 9 ký tự")
    private String phone;

    private String gender;
    private Date dateOfBirth;
}
