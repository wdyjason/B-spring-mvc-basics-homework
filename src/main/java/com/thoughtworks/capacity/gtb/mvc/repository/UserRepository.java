package com.thoughtworks.capacity.gtb.mvc.repository;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Data
public class UserRepository {
    private List<User> userList = new ArrayList<>();

    public User save(User newUser) {
        newUser.setId(userList.size() + 1);
        userList.add(newUser);
        return newUser;
    }

    public List<User> findAll() {
        return this.userList;
    }

    public Optional<User> findOneByUsername(String username) {
        for (User user : this.userList) {
            if (user.getUsername().equals(username)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
