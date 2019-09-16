package com.mr.egzamator.service;

import com.mr.egzamator.dto.UserDTO;
import com.mr.egzamator.model.Role;
import com.mr.egzamator.model.Student;
import com.mr.egzamator.model.User;
import com.mr.egzamator.respository.RoleRepository;
import com.mr.egzamator.respository.StudentRepository;
import com.mr.egzamator.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private StudentRepository studentRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public User register(UserDTO userDTO) {
        Optional<User> user = userRepository.findByEmailAndIndexNumber(userDTO.getEmail(), userDTO.getIndexNumber());

        if (!user.isPresent()) {
            User newUser = buildUser(userDTO);
            log.info("Registering new user: " + newUser);
            userRepository.save(newUser);
            log.info("New user created");
            return newUser;
        } else {
            log.info("User already signed up");
            return user.get();
        }
    }

    private User buildUser(UserDTO userDTO) {
        System.out.println(userDTO);
        User newUser = new User();
        newUser.setEmail(userDTO.getEmail());
        newUser.setIndexNumber((Integer) userDTO.getIndexNumber());
        newUser.setUserIdentity(userDTO.getUserId());
        Optional<Role> role = roleRepository.findByRole("ROLE_USER");
        Role newRole;
        if(!role.isPresent()){
            log.info("Registering new role: ROLE_USER");
            newRole = new Role();
            newRole.setRole("ROLE_USER");
            roleRepository.saveAndFlush(newRole);

            log.info("New role created");
//            newUser.setRole(roleRepository.findByName(newRole.getRole()));
            newUser.setRole(newRole);
            Student newStudent = new Student();
            studentRepository.save(newStudent);
            newUser.setStudent(newStudent);

        } else {
            newUser.setRole(role.get());
        }

//        Optional<Student> student = studentRepository.findByIndexNumber

        return newUser;
    }

    public String findUserRole(String userId) {
        log.info("Looking for " + userId + " user role...");
        String userRole =  userRepository.findUserRole(userId);
        return userRole == null ? "NONE" : userRole;
    }

    public User checkUser(String email, Integer indexNumber) {
        Optional<User> userEmail = userRepository.findByEmailAndIndexNumber(email, indexNumber);
        return userEmail.orElse(null);
    }
}
