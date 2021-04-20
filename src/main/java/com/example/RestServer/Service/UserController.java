package com.example.RestServer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.List;

@Controller
public class UserController {

    //Call to use methods from UserService class
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String  index  () {return "home"; }

    @GetMapping("/home")
    public String homePage () {
        return "home";
    }

    @GetMapping("/products")
    public String productPage () {return "products"; }

    @GetMapping ("/info")
    public String infoPage () {return "info"; }



}
