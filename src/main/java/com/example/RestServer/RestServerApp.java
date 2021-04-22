package com.example.RestServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServerApp {

    //Main methos for application
    //localhost:8080/path

    public static void main(String[] args) {
        SpringApplication.run(RestServerApp.class, args);
    }
}
