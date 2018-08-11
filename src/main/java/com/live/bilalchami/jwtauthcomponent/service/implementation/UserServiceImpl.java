package com.live.bilalchami.jwtauthcomponent.service.implementation;

import com.live.bilalchami.jwtauthcomponent.model.User;
import com.live.bilalchami.jwtauthcomponent.repository.UserRepository;
import com.live.bilalchami.jwtauthcomponent.security.JwtProvider;
import com.live.bilalchami.jwtauthcomponent.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String signIn(String username, String password) throws Exception {
        User user = findByUsername(username);
        if (user!=null && user.getPassword().equals(password)){
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                return jwtProvider.createToken(username, userRepository.findByUsername(username).getRoles());
            } catch (AuthenticationException e) {
                throw new Exception("Invalid username/password supplied");
            }
        }
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
        User user = userRepository.findByUsername(username);
        if (user == null) {
//            throw new Exception("The user doesn't exist");
            System.out.println("The user doesn't exist");
        }
        return user;
    }

    @Override
    public User whoAmI(HttpServletRequest req) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
