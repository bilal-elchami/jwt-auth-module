package com.live.bilalchami.jwtauthcomponent.service.implementation;

import com.live.bilalchami.jwtauthcomponent.model.User;
import com.live.bilalchami.jwtauthcomponent.repository.UserRepository;
import com.live.bilalchami.jwtauthcomponent.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String signIn(String username, String password) {
        return null;
    }

    @Override
    public String signUp(User user) {
        return null;
    }

    @Override
    public void delete(String username) {

    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User whoAmI(HttpServletRequest req) {
        return null;
    }

}
