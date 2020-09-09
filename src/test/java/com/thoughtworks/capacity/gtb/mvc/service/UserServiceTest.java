package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.Exception.UserAlreadyExistedException;
import com.thoughtworks.capacity.gtb.mvc.Exception.LoginFailedException;
import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void shouldRegisterUserSuccessfully() throws UserAlreadyExistedException {
        User testUser = User.builder()
                .username("test")
                .password("psw")
                .email("test@qq.com")
                .build();

        User returnUser = User.builder()
                .id(1)
                .username("test")
                .password("psw")
                .email("test@qq.com")
                .build();

        when(userRepository.save(testUser)).thenReturn(returnUser);

        User result = userService.save(testUser);

        verify(userRepository, times(1)).save(testUser);
        assertEquals(returnUser, result);
    }

    @Test
    public void shouldRegisterSameUserFailed() {
        User testUser = User.builder()
                .username("test")
                .password("psw")
                .email("test@qq.com")
                .build();

        User returnUser = User.builder()
                .id(1)
                .username("test")
                .password("psw")
                .email("test@qq.com")
                .build();

        when(userRepository.findAll()).thenReturn(Arrays.asList(returnUser));

        assertThrows(UserAlreadyExistedException.class, () -> {
            userService.save(testUser);
        } );

        verify(userRepository, times(0)).save(testUser);
    }

    @Test
    public void shouldLoginSuccessfully() throws Exception {
        String username = "testUser";
        String password = "testPassword";

        User returnUser = User.builder()
                .id(1)
                .username("testUser")
                .password("testPassword")
                .email("test@qq.com")
                .build();

        when(userRepository.findOneByUsername(username)).thenReturn(Optional.of(returnUser));

        User result = userService.login(username, password);

        assertEquals(result, returnUser);
    }
}