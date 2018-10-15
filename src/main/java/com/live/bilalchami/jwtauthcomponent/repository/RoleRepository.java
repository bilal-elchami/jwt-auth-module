package com.live.bilalchami.jwtauthcomponent.repository;

import com.live.bilalchami.jwtauthcomponent.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByAuthority(String authority);

    @Transactional
    void deleteByAuthority(String authority);


}
