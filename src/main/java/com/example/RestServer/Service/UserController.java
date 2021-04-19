package com.example.RestServer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    //Call to use methods from UserService class
    /*@Autowired
    private UserService userService;*/

    @GetMapping("/")
    public String homePage () {
        return "This is the homepage for Michael Ekornrud, Portfolio 2 DATA2410\n Vilde er bestest i verden";
    }



}
