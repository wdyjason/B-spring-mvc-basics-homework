package com.thoughtworks.capacity.gtb.mvc.api;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        userRepository.getUserList().clear();
    }

    @Test
    public void shouldRegisterUserSuccessfully() throws Exception {
        String postStr = "{\"username\": \"Tom\", \"password\": \"12345\", \"email\": \"tom@qq.com\"}";
        mockMvc.perform( post("/register").content(postStr).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        assertEquals(1, userRepository.getUserList().size());
    }

    @Test
    public void shouldLoginSuccessfully() throws Exception {
        User user = User.builder()
                .id(1)
                .username("foo")
                .password("bar")
                .email("test@qq.com")
                .build();
        userRepository.getUserList().add(user);

        mockMvc.perform(get("/login?username=foo&password=bar"))
                .andExpect(jsonPath("$.username", is("foo")))
                .andExpect(jsonPath("$.password", is("bar")))
                .andExpect(jsonPath("$.email", is("test@qq.com")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(status().isOk());

    }
}