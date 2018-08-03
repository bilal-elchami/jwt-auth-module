package com.live.bilalchami.jwtauthcomponent.service.interfaces;

import com.live.bilalchami.jwtauthcomponent.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    List<User> findAll();

    String signIn(String username, String password);

    String signUp(User user);

    void delete(String username);

    User findByUsername(String username);

    User whoAmI(HttpServletRequest req);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
