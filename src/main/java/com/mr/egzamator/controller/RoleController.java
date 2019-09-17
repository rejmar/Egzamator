package com.mr.egzamator.controller;

import com.mr.egzamator.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/role")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/addRole")
    public void addRole(@RequestParam String roleName) {
        roleService.addNewRole(roleName);
    }
}
