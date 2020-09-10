package com.thoughtworks.capacity.gtb.mvc.api;

import com.thoughtworks.capacity.gtb.mvc.Exception.LoginFailedException;
import com.thoughtworks.capacity.gtb.mvc.Exception.UserRepetitionException;
import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import javafx.fxml.LoadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {


    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody @Valid User user) throws UserRepetitionException {
        userService.save(user);
    }
    @GetMapping("/login")
    public User login(@RequestParam String username, @RequestParam String password) throws Exception {
        return userService.login(username, password);
    }
}