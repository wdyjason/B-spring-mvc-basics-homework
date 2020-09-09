package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldRegisterUserSuccessfully() {
        User testUser = User.builder()
                .name("test")
                .password("psw")
                .email("test@qq.com")
                .build();

        User returnUser = User.builder()
                .id(1)
                .name("test")
                .password("psw")
                .email("test@qq.com")
                .build();

        when(userRepository.save(testUser)).thenReturn(returnUser);

        User result = userService.save(testUser);

        verify(userRepository, times(1)).save(testUser);
        assertEquals(returnUser, result);

    }
}