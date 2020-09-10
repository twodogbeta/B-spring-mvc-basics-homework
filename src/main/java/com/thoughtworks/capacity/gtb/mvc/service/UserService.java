package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.Exception.LoginFailedException;
import com.thoughtworks.capacity.gtb.mvc.Exception.UserNotFoundException;
import com.thoughtworks.capacity.gtb.mvc.Exception.UserRepetitionException;
import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.repository.UserRepository;
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
        for (User u : allUser) {
            if (u.getUsername().equals(user.getUsername())) {
                throw new UserRepetitionException("用户名已存在！");
            }
        }
        return userRepository.save(user);
    }

    public User login(String username, String password) throws Exception {
        Optional<User> user = userRepository.findUserByUsername(username);

        if (user.isPresent()) {
            if (isCorrectPassword(user.get(), password)) return user.get();
            throw new LoginFailedException("用户名或密码错误！");
        }
        throw new UserNotFoundException("用户不存在");
    }

    public boolean isCorrectPassword(User user, String password) {
        return user.getPassword().equals(password);
    }
}