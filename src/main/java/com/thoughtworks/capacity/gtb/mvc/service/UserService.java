package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.Exception.LoginFailedException;
import com.thoughtworks.capacity.gtb.mvc.Exception.UserRepetitionException;
import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User save(User user) throws UserRepetitionException {
        List<User> allUser = userRepository.findAll();
        for(User u : allUser) {
            if (u.getName().equals(user.getName())) {
                throw new UserRepetitionException("用户名已存在！");
            }
        }
        return userRepository.save(user);
    }

    public User login(String username, String password) throws LoginFailedException {
        Optional<User> userResult = userRepository.findUserByUsername(username);
        if (userResult.isPresent() && isCorrectPassword(userResult.get(), password)) {
            return userResult.get();
        }
        throw new LoginFailedException("账号或密码错误！");
    }

    public boolean isCorrectPassword(User user, String password) {
        return user.getPassword().equals(password);
    }
}