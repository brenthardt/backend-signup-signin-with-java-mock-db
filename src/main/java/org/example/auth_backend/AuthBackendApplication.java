package org.example.auth_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.example.auth_backend.Db.generateUsers;

@SpringBootApplication
public class AuthBackendApplication {

    public static void main(String[] args) {
        generateUsers();
        SpringApplication.run(AuthBackendApplication.class, args);
    }

}
