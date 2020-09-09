package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.Exception.UserAlreadyExistedException;
import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User save(User newUser) throws UserAlreadyExistedException {
        List<User> allUser = userRepository.findAll();
        for(User u : allUser) {
            if (u.getUsername().equals(newUser.getUsername())) {
                throw new UserAlreadyExistedException("This User Name Already Existed!");
            }
        }
        return userRepository.save(newUser);
    }
}
