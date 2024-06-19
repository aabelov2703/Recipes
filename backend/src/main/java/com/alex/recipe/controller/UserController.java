package com.alex.recipe.controller;

import com.alex.recipe.model.User;
import com.alex.recipe.repository.UserRepo;
import com.alex.recipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/profile")
    public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
        return userService.findUserByJwt(jwt);
    }
}
