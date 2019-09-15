package com.mr.egzamator.controller;

import com.mr.egzamator.dto.UserDTO;
import com.mr.egzamator.model.User;
import com.mr.egzamator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registerUser")
    void registerNewUser(@RequestBody UserDTO userDTO){
        System.out.println("Received: " + userDTO);
        userService.register(userDTO);
    }
}
