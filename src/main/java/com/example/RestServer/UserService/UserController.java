package com.example.RestServer.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {

    //Call to use methods from UserService class
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String  index  () {return "home"; }

    @GetMapping("/home")
    public String homePage () {return "home";}

    @GetMapping("/products")
    public String productPage () {return "products"; }

    @GetMapping ("/info")
    public String infoPage () {return "info"; }

    //Funker ikke
    @GetMapping ("/api/users")
    @ResponseBody
    public List<User> getUsers () { return userService.getAllUsers();}

    //Funker ikke
    @GetMapping ("/bob")
    @ResponseBody
    public String bob () {return "Halla bob!";}
}
