package com.live.bilalchami.jwtauthcomponent.controller;

import com.live.bilalchami.jwtauthcomponent.model.User;
import com.live.bilalchami.jwtauthcomponent.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/signin")
    public String signIn(@RequestParam String username,
                         @RequestParam String password) {
        return userService.signIn(username, password);
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody User user) {
        return userService.signUp(user);
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User search(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public User whoami(HttpServletRequest req) {
        return userService.whoAmI(req);
    }

}