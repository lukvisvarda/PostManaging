package com.example.amcef.users.logic;

import com.example.amcef.exception.NotFoundException;
import com.example.amcef.users.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService implements IUserService{

    @Autowired
    private RestTemplate restTemplate;

    String USER_API = "https://jsonplaceholder.typicode.com/users/";

    @Override
    public User[] getAll() {
        ResponseEntity<User[]> response = restTemplate.exchange(USER_API, HttpMethod.GET, null, User[].class );
        return response.getBody();
    }

    @Override
    public User getUserById(String userId) throws NotFoundException {
        String url = USER_API + userId;
        ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.GET, null, User.class );
        return response.getBody();
    }
}
