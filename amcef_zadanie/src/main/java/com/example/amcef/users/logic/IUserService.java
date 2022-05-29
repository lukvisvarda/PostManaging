package com.example.amcef.users.logic;

import com.example.amcef.exception.NotFoundException;
import com.example.amcef.users.data.User;

public interface IUserService {
    User[] getAll();

    User getUserById(String userId) throws NotFoundException;
}
