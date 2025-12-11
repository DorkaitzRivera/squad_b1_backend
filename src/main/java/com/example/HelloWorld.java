package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.jdbc.core.JdbcTemplate; 

@SpringBootApplication
public class HelloWorld { 

    public static void main(String[] args) {
        SpringApplication.run(HelloWorld.class, args);
    }
    
    @RestController
    public static class HelloController {
        
        @Autowired 
        private JdbcTemplate jdbcTemplate; 

        // Endpoint 1: Simple Health Check / Hello World
        @GetMapping("/")
        public String index() {
            return "Hello World (Java 21) from Spring Boot on Cloud Run! Use /test-db to check database connection.";
        }

        // Endpoint 2: Database Connection Test
        @GetMapping("/test-db")
        public String testDb() {
            // SQL query to select the specific column and row
            String sql = "SELECT \"name\" FROM \"products\" LIMIT 1";
            
            try {
                // Execute the query and map the result to a String
                String result = jdbcTemplate.queryForObject(sql, String.class);
                return "DB Connection SUCCESS! Row value found: " + result;
            } catch (Exception e) {
                // Return an error message if the connection or query fails
                return "DB Query FAILED. Check DB connection, table, and column existence. Error: " + e.getMessage();
            }
        }
    }
}