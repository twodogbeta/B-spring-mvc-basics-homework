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
    private final List<User> userList = new ArrayList<>();

    public User save(User user) {
        user.setId(userList.size() + 1);
        userList.add(user);
        return user;
    }
    public List<User> findAll() {
        return this.userList;
    }
    public Optional<User> findUserByUsername(String username) {
        for (User user : this.userList) {
            if (user.getUsername().equals(username)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}