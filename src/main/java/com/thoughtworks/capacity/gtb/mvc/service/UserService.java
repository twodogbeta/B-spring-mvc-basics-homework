package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.Exception.UserRepetitionException;
import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                throw new UserRepetitionException("This User Name Already Existed!");
            }
        }
        return userRepository.save(user);
    }
}