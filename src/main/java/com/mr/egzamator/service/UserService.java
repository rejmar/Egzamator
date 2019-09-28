package com.mr.egzamator.service;

import com.mr.egzamator.dto.UserDTO;
import com.mr.egzamator.exception.EgzamatorException;
import com.mr.egzamator.model.Role;
import com.mr.egzamator.model.Student;
import com.mr.egzamator.model.User;
import com.mr.egzamator.respository.RoleRepository;
import com.mr.egzamator.respository.StudentRepository;
import com.mr.egzamator.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public User register(UserDTO userDTO) throws EgzamatorException {
        Optional<User> user = userRepository.findByEmailAndIndexNumber(userDTO.getEmail(), userDTO.getIndexNumber());

        if (!user.isPresent()) {
            User newUser = buildUser(userDTO);
            log.info(" new user: " + newUser);
            userRepository.saveAndFlush(newUser);
            log.info("New user created");
            return newUser;
        } else {
            throw new EgzamatorException("User already signed up");
        }
    }

    private User buildUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setEmail(userDTO.getEmail());
        newUser.setIndexNumber((Integer) userDTO.getIndexNumber());
        newUser.setUserIdentity(userDTO.getUserId());
        Optional<Role> role = roleRepository.findByName("ROLE_STUDENT");
        Role newRole;
        if (!role.isPresent()) {
            log.info("Registering new role: ROLE_STUDENT");
            newRole = new Role();
            newRole.setName("ROLE_STUDENT");
            roleRepository.saveAndFlush(newRole);
            log.info("New role created");
            newUser.setRole(newRole);
        } else {
            newUser.setRole(role.get());
        }
        log.info("Creating new student");
        Student newStudent = new Student();
        newStudent.setUser(newUser);
        log.info("New student created");
        newUser.setStudent(newStudent);

        return newUser;
    }

    public String findUserRole(String userId) {
        log.info("Looking for " + userId + " user role...");
        String userRole = userRepository.findUserRole(userId);
        return userRole == null ? "NONE" : userRole;
    }

    public User checkUser(String email, Integer indexNumber) throws EgzamatorException {
        log.info("Checking existance of user with email: " + email + ", indexNumber: " + indexNumber);
        List<User> users = userRepository.getAllUsersByEmailOrIndexNumber(email, indexNumber);

        if (users.size() == 1) {
            User user = users.get(0);
            log.info("User found: " + user);
            return user;
        } else {
            throw new EgzamatorException("User not found or is not unique: " + users);
        }
    }

    public User getUser(String userId) {
        return userRepository.findByUserIdentity(userId).orElse(null);
    }
}
