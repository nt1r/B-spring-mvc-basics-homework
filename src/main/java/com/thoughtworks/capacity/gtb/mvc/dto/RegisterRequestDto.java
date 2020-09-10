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
    @NotNull
    @Size(min = 3, max = 10)
    @Pattern(regexp = "^[0-9a-zA-Z_]+$")
    String username;

    @NotNull
    @Size(min = 5, max = 12)
    String password;

    @Email
    String email;
}
