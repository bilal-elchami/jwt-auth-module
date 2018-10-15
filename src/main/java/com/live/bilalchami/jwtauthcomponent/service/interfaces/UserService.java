package com.live.bilalchami.jwtauthcomponent.service.interfaces;

import com.live.bilalchami.jwtauthcomponent.exceptions.UsernameAlreadyExistsException;
import com.live.bilalchami.jwtauthcomponent.model.User;
import com.live.bilalchami.jwtauthcomponent.security.model.JwtToken;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    List<User> findAll();

    JwtToken signIn(String username, String password) throws Exception;

    JwtToken signUp(User user) throws UsernameAlreadyExistsException;

    void delete(String username);

    User findByUsername(String username);

    User whoAmI(HttpServletRequest req);

}
