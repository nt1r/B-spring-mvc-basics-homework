package com.thoughtworks.capacity.gtb.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDto {
    @NotNull(message = "用户名不能为空")
    @Size(min = 3, max = 10, message = "用户名长度应为3到10位")
    @Pattern(regexp = "^[0-9a-zA-Z_]+$", message = "用户名只能由字母、数字或下划线组成")
    String username;

    @NotNull(message = "密码不能为空")
    @Size(min = 5, max = 12, message = "密码长度应为5到12位")
    String password;

    @Email(message = "邮箱地址不合法")
    String email;
}
