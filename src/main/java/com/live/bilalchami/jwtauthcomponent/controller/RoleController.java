package com.live.bilalchami.jwtauthcomponent.controller;

import com.live.bilalchami.jwtauthcomponent.model.Role;
import com.live.bilalchami.jwtauthcomponent.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAllUsers() {
        return roleService.findAll();
    }

}
