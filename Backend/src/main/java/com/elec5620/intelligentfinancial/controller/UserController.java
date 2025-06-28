package com.elec5620.intelligentfinancial.controller;

import com.elec5620.intelligentfinancial.model.Response.UserResponse;
import com.elec5620.intelligentfinancial.repository.UserRepository;
import com.elec5620.intelligentfinancial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:3001")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRegisterRequest request) {
        UserResponse response = userService.registerUser(request.role, request.email, request.username, request.password);
        if (response.getResponseCode().startsWith("F")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(response);
    }

   @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        UserResponse response = userService.loginUser(loginRequest.email, loginRequest.password);
        if (response.getResponseCode().startsWith("F")) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(response);
    }

    public static class UserRegisterRequest {
        public String role;
        public String email;
        public String username;
        public String password;
    }

    public static class LoginRequest {
        public String email;
        public String password;
    }
}
