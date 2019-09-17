package com.mr.egzamator.controller;

import com.mr.egzamator.dto.UserDTO;
import com.mr.egzamator.model.User;
import com.mr.egzamator.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
    public User registerNewUser(@RequestBody UserDTO userDTO){
        return userService.register(userDTO);
    }

    @GetMapping("/role")
    public String getUserRole(@RequestParam String userId) {
        return userService.findUserRole(userId);
    }

    @GetMapping("/checkUser")
    public User checkUserIfExist(@RequestParam String email, @RequestParam Integer indexNumber){
        return userService.checkUser(email, indexNumber);
    }
}
