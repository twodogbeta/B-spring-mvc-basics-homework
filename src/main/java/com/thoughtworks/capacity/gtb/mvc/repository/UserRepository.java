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

    public User save(User user) {
        user.setId(userList.size() + 1);
        userList.add(user);
        return user;
    }
    public List<User> findAll() {
        return this.userList;
    }
}