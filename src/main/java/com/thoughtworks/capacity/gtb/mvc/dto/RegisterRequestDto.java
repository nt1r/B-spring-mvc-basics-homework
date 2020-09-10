package com.thoughtworks.capacity.gtb.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDto {
    @NotNull(message = "用户名不能为空")
    @Size(min = 3, max = 10, message = "用户名长度应为3到10位")
    @Pattern(regexp = "^[0-9a-zA-Z_]+$", message = "用户名只能由字母、数字或下划线组成")
    String username;

    @NotNull
    @Size(min = 5, max = 12)
    String password;

    @Email
    String email;
}
