package com.thoughtworks.capacity.gtb.mvc.entity;

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
public class User {
    @NotNull
    Integer id;

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
