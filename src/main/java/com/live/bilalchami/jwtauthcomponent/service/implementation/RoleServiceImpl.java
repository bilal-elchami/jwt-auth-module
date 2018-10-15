package com.live.bilalchami.jwtauthcomponent.service.implementation;

import com.live.bilalchami.jwtauthcomponent.model.Role;
import com.live.bilalchami.jwtauthcomponent.repository.RoleRepository;
import com.live.bilalchami.jwtauthcomponent.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void delete(String authority) {
        this.roleRepository.deleteByAuthority(authority);
    }

    @Override
    public Role findByAuthority(String authority) {
        return this.roleRepository.findByAuthority(authority);
    }
}
