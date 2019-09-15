package com.mr.egzamator.service;

import com.mr.egzamator.dto.UserDTO;
import com.mr.egzamator.model.Role;
import com.mr.egzamator.model.User;
import com.mr.egzamator.respository.UserRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserDTO userDTO) {


        Optional<User> u = userRepository.findByEmail(userDTO.getEmail());
        if (!u.isPresent()) {
            User newUser = buildUser(userDTO);
            userRepository.save(newUser);
            System.out.println("New user created: " + newUser);
        }
    }

    private User buildUser(UserDTO userDTO) {
        System.out.println(userDTO);
        User newUser = new User();
        newUser.setEmail(userDTO.getEmail());
        newUser.setIndexNumber((Integer) userDTO.getIndexNumber());
        newUser.setUserIdentity(userDTO.getUserId());
        Role role = new Role();
        role.setRole("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        newUser.setRoles(roles);
        return newUser;
    }

}
