package com.thoughtworks.capacity.gtb.mvc.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capacity.gtb.mvc.dto.RegisterRequestDto;
import com.thoughtworks.capacity.gtb.mvc.service.RegisterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RegisterControllerTest {
    private final String registerUrl = "/register";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RegisterService registerService;

    @BeforeEach
    void setUp() {
        RegisterService.userMap.clear();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void loadContextSuccess() {

    }

    @Test
    public void shouldRegisterSuccess() throws Exception {
        RegisterRequestDto registerRequestDto = RegisterRequestDto.builder()
                .username("Tom")
                .password("123456")
                .email("tom@qq.com")
                .build();

        mockMvc.perform(post(registerUrl).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequestDto)))
                .andExpect(status().isCreated());

        assertEquals(1, RegisterService.userMap.size());
    }

    @Test
    public void shouldRegisterFailedWhenUsernameDuplicated() throws Exception {
        RegisterRequestDto tomRequest = RegisterRequestDto.builder()
                .username("Tom")
                .password("123456")
                .email("tom@qq.com")
                .build();

        mockMvc.perform(post(registerUrl).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tomRequest)))
                .andExpect(status().isCreated());

        RegisterRequestDto secondTomRequest = RegisterRequestDto.builder()
                .username("Tom")
                .password("654321")
                .email("tom2@qq.com")
                .build();

        mockMvc.perform(post(registerUrl).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(secondTomRequest)))
                .andExpect(jsonPath("$.message", is("用户已存在")))
                .andExpect(status().isBadRequest());

        assertEquals(1, RegisterService.userMap.size());
    }
}