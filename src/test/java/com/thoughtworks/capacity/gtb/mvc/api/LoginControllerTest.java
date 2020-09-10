package com.thoughtworks.capacity.gtb.mvc.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capacity.gtb.mvc.entity.User;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
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
class LoginControllerTest {
    private final String loginUrl = "/login?username=%s&password=%s";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
        UserService.userMap.clear();
        UserService.userMap.put(0, new User(0, "Tom", "123456", "tom@qq.com"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void loadContext() { }

    @Test
    public void shouldLoginSuccess() throws Exception {
        mockMvc.perform(get(String.format(loginUrl, "Tom", "123456"))
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldLoginFailedWhenUsernameIsNull() throws Exception {
        mockMvc.perform(get(String.format(loginUrl, "", "123456"))
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldLoginFailedWhenUsernameTooLong() throws Exception {
        mockMvc.perform(get(String.format(loginUrl, "leqqqqqqqqqqqqqqq", "123456"))
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldLoginFailedWhenUsernameTooShort() throws Exception {
        mockMvc.perform(get(String.format(loginUrl, "lq", "123456"))
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldLoginFailedWhenUsernameContainsInvalidCharacters() throws Exception {
        mockMvc.perform(get(String.format(loginUrl, "@le#qi$", "123456"))
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldLoginFailedWhenPasswordIsNull() throws Exception {
        mockMvc.perform(get(String.format(loginUrl, "Tom", ""))
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldLoginFailedWhenPasswordTooShort() throws Exception {
        mockMvc.perform(get(String.format(loginUrl, "Tom", "123"))
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldLoginFailedWhenPasswordTooLong() throws Exception {
        mockMvc.perform(get(String.format(loginUrl, "Tom", "123456789101112"))
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }
}