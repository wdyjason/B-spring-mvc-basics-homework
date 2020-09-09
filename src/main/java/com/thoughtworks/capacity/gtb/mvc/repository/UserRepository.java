package com.thoughtworks.capacity.gtb.mvc.repository;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Data
public class UserRepository {
    private List<User> userList = new ArrayList<>();

    public User save(User newUser) {
        newUser.setId(userList.size() + 1);
        userList.add(newUser);
        return newUser;
    }
}
