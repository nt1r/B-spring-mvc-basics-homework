package com.thoughtworks.capacity.gtb.mvc.api;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                .andExpect(status().isOk());

        assertEquals(1, RegisterService.userMap.size());
    }
}