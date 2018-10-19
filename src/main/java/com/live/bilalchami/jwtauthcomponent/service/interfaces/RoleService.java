package com.live.bilalchami.jwtauthcomponent.service.interfaces;

import com.live.bilalchami.jwtauthcomponent.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    void delete(String name);

    Role findByAuthority(String authority);

}
