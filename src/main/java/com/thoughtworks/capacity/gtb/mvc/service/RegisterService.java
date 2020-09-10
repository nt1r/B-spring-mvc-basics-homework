package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.dto.RegisterRequestDto;
import com.thoughtworks.capacity.gtb.mvc.entity.User;
import com.thoughtworks.capacity.gtb.mvc.exception.RegisterError;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterService {
    private Integer autoIncreaseId = 0;
    public static final Map<Integer, User> userMap = new HashMap<>();

    public void register(RegisterRequestDto registerRequestDto) {
        if (checkUsernameDuplicated(registerRequestDto)) {
            throw new RegisterError("用户已存在");
        }

        User user = User.builder()
                .id(autoIncreaseId)
                .username(registerRequestDto.getUsername())
                .password(registerRequestDto.getPassword())
                .email(registerRequestDto.getEmail())
                .build();
        userMap.put(autoIncreaseId++, user);
    }

    private boolean checkUsernameDuplicated(RegisterRequestDto registerRequestDto) {
        boolean duplicated = false;
        for (int id: userMap.keySet()) {
            User user = userMap.get(id);
            if (user.getUsername().equals(registerRequestDto.getUsername())) {
                duplicated = true;
                break;
            }
        }
        return duplicated;
    }
}
