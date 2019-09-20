package com.mr.egzamator.service;

import com.mr.egzamator.model.Role;
import com.mr.egzamator.respository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addNewRole(String roleName) {
        log.info("Looking for role: " + roleName);
        Optional<Role> oRole = roleRepository.findByName(roleName);

        if (oRole.isPresent()) {
            log.info("Creating new role");
            Role newRole = oRole.get();
            newRole.setName(roleName.toUpperCase());
            roleRepository.save(newRole);
            log.info(roleName + " newRole");
        } else {
            log.info(roleName + " already exists.");
        }
    }
}
