package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.dto.LoginResponseDto;
import com.thoughtworks.capacity.gtb.mvc.dto.RegisterRequestDto;
import com.thoughtworks.capacity.gtb.mvc.entity.User;
import com.thoughtworks.capacity.gtb.mvc.exception.PasswordNotCorrectException;
import com.thoughtworks.capacity.gtb.mvc.exception.RegisterUsernameDuplicatedError;
import com.thoughtworks.capacity.gtb.mvc.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private Integer autoIncreaseId = 0;
    public static final Map<Integer, User> userMap = new HashMap<>();

    public void register(RegisterRequestDto registerRequestDto) {
        if (isUsernameContained(registerRequestDto)) {
            throw new RegisterUsernameDuplicatedError("用户已存在");
        }

        User user = User.builder()
                .id(autoIncreaseId)
                .username(registerRequestDto.getUsername())
                .password(registerRequestDto.getPassword())
                .email(registerRequestDto.getEmail())
                .build();
        userMap.put(autoIncreaseId++, user);
    }

    private boolean isUsernameContained(RegisterRequestDto registerRequestDto) {
        boolean isDuplicated = false;
        for (User user: userMap.values()) {
            if (user.getUsername().equals(registerRequestDto.getUsername())) {
                isDuplicated = true;
                break;
            }
        }
        return isDuplicated;
    }

    public LoginResponseDto login(String username, String password) {
        User user = findUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("用户不存在");
        }
        if (isPasswordIncorrect(user, password)) {
            throw new PasswordNotCorrectException("登录密码错误");
        }

        return LoginResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    private boolean isPasswordIncorrect(User user, String password) {
        return !user.getPassword().equals(password);
    }

    private User findUserByUsername(String username) {
        Optional<User> userOptional = userMap.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
        return userOptional.orElse(null);
    }
}
