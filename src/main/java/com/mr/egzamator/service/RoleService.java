package com.mr.egzamator.service;

import com.mr.egzamator.model.Role;
import com.mr.egzamator.respository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Role newRole = roleRepository.findByName(roleName);

        if (newRole == null) {
            log.info("Creating new role");
            newRole = new Role();
            newRole.setRole(roleName.toUpperCase());
            roleRepository.save(newRole);
            log.info(roleName + " created");
        } else {
            log.info(roleName + " already exists.");
        }
    }
}
