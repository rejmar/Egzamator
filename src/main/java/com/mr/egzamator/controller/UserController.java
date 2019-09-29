package com.mr.egzamator.controller;

import com.mr.egzamator.dto.UserDTO;
import com.mr.egzamator.exception.EgzamatorException;
import com.mr.egzamator.model.User;
import com.mr.egzamator.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registerUser")
    public User registerNewUser(@RequestBody UserDTO userDTO){
        try {
            return userService.register(userDTO);
        } catch (EgzamatorException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
    }

    @GetMapping("/role")
    public String getUserRole(@RequestParam String userId) {
        return userService.findUserRole(userId);
    }

    @PostMapping("/checkUser")
    public User checkUserIfExist(@RequestParam String email, @RequestParam Integer indexNumber){
        try {
            return userService.checkUser(email, indexNumber);
        } catch (EgzamatorException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/getUser")
    public User getUser(@RequestParam String userId) {
        return userService.getUser(userId);
    }
}
