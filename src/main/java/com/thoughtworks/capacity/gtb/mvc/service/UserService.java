package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.Exception.NotFoundException;
import com.thoughtworks.capacity.gtb.mvc.Exception.UserAlreadyExistedException;
import com.thoughtworks.capacity.gtb.mvc.Exception.LoginFailedException;
import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public User login(String username, String password) throws Exception {
        Optional<User> userResult = userRepository.findOneByUsername(username);
        if (userResult.isPresent()) {
            if(isCorrectPassword(userResult.get(), password)) return userResult.get();
            throw new LoginFailedException("This username or password incorrect!");
        }
        throw new NotFoundException("This user is not existed !");
    }

    public boolean isCorrectPassword(User user, String password) {
        return user.getPassword().equals(password);
    }
}
