package com.live.bilalchami.jwtauthcomponent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.live.bilalchami.jwtauthcomponent.model.base.BaseEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role extends BaseEntity implements GrantedAuthority {

    private String name;
    private String authority;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
        user.getRoles().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getRoles().remove(this);
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", authority='" + authority + '\'' +
//                ", users=" + users +
                '}';
    }
}
