package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Servicio {

    // Endpoint 1: Simple Health Check / Hello World
    @GetMapping("/a/")
    public String index() {
        return "Hello World (Java 21) from Spring Boot on Cloud Run! Use /test-db to check database connection.";
    }

}
