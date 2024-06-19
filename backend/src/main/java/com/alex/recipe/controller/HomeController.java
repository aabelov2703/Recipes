package com.alex.recipe.controller;

import com.alex.recipe.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String homeController() {
        return "Welcome back";
    }

}
