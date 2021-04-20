package com.example.RestServer.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class UserService {

    public static List<User> userList = new ArrayList<>(
            Arrays.asList(new User("1", "Michael", "Halla balla!"))
    );

    public List<User> getAllUsers() {return userList;}
}
