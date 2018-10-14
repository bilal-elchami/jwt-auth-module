package com.live.bilalchami.jwtauthcomponent.service.implementation;

import com.live.bilalchami.jwtauthcomponent.exceptions.UsernameAlreadyExistsException;
import com.live.bilalchami.jwtauthcomponent.model.User;
import com.live.bilalchami.jwtauthcomponent.repository.UserRepository;
import com.live.bilalchami.jwtauthcomponent.security.JwtProvider;
import com.live.bilalchami.jwtauthcomponent.service.interfaces.UserService;
import com.live.bilalchami.jwtauthcomponent.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Autowired
    private PasswordUtils passwordUtils;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String signIn(String username, String password) throws BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    }

    @Override
    public String signUp(User user) throws UsernameAlreadyExistsException {
        if (!userRepository.existsByUsername(user.getUsername())) {

            user.setSalt(passwordUtils.nextSalt());
            user.setPassword(passwordUtils.toSHA256(user.getSalt() + user.getPassword()));
            user = userRepository.save(user);

            // System.out.println(userRepository.getOne(user.getId()).getRoles());
            // user = userRepository.findByUsername(user.getUsername());
            return jwtProvider.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new UsernameAlreadyExistsException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
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
        String token = this.jwtProvider.resolveToken(req);
        if (token != null && this.jwtProvider.validateToken(token)) {
            return this.userRepository.findByUsername(jwtProvider.getUsername(token));
        }
        return null;
    }

}
