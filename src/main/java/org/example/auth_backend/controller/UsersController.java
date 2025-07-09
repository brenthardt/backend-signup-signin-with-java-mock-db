package org.example.auth_backend.controller;

import jakarta.validation.Valid;
import org.example.auth_backend.Status;
import org.example.auth_backend.dto.SignInRequest;
import org.example.auth_backend.entity.Users;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.auth_backend.Db.users;


@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping
    public List<Users> getUsers(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Integer age) {

        return users.stream()
                .filter(user -> (status == null || user.getStatus().equals(status)) &&
                        (age == null || user.getAge() <= age))
                .collect(Collectors.toList());
    }

    @PostMapping("/signup")
    public String signup(@Valid @RequestBody Users user) {
        for (Users existingUser : users) {
            if (existingUser.getEmail().equals(user.getEmail())) {
                return "User already exists.";
            }
        }
        int newId = users.isEmpty() ? 1 : users.get(users.size() - 1).getId() + 1;
        user.setId(newId);
        users.add(user);
        return "User signed up successfully!";
    }


    @PostMapping("/signin")
    public Map<String, Object> signIn(@RequestBody SignInRequest credentials) {
        Map<String, Object> response = new HashMap<>();

        if (credentials == null || credentials.getEmail() == null || credentials.getPassword() == null) {
            response.put("success", false);
            response.put("message", "Provide email and password.");
            return response;
        }

        String email = credentials.getEmail().toLowerCase();
        String password = credentials.getPassword();

        for (Users user : users) {
            if (user.getEmail().toLowerCase().equals(email) && user.getPassword().equals(password)) {
                String token = generateToken(user);
                response.put("success", true);
                response.put("message", "Sign-in successful");
                response.put("token", token);
                return response;
            }
        }

        response.put("success", false);
        response.put("message", "Invalid email or password");
        return response;
    }

    private String generateToken(Users user) {
        return "token_" + user.getId() + "_" + System.currentTimeMillis();
    }





}
