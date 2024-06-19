package com.alex.recipe.controller;

import com.alex.recipe.model.Recipe;
import com.alex.recipe.model.User;
import com.alex.recipe.service.RecipeService;
import com.alex.recipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Recipe> getAllRecipe() {
        return recipeService.findAllRecipe();
    }

    @PostMapping("")
    public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return recipeService.createRecipe(recipe, user);
    }

    @DeleteMapping("{id}")
    public String deleteRecipe(@PathVariable Long id) throws Exception {
        recipeService.deleteRecipe(id);
        return "Recipe deleted";
    }

    @PutMapping("{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception {
        return recipeService.updateRecipe(recipe, id);
    }

    @PutMapping("/{id}/like")
    public Recipe likeRecipe(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return recipeService.likeRecipe(id, user);
    }
}
