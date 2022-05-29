package com.example.amcef.users.web;

import com.example.amcef.exception.NotFoundException;
import com.example.amcef.users.data.User;
import com.example.amcef.users.logic.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping
    public User[] getAllUsers() {
        return this.service.getAll();
    }

    @GetMapping(path = "/{userId}")
    public User getUserById(@PathVariable("userId") String userId) throws NotFoundException {
        return this.service.getUserById(userId);
    }

}
