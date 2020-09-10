package com.thoughtworks.capacity.gtb.mvc.api;

import com.thoughtworks.capacity.gtb.mvc.dto.LoginResponseDto;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/login")
@Validated
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public LoginResponseDto login(@RequestParam @NotNull @Size(min = 3, max = 10) @Pattern(regexp = "^[0-9a-zA-Z_]+$") String username,
                                  @RequestParam @NotNull @Size(min = 5, max = 12) String password)
    {
        return userService.login(username, password);
    }
}
