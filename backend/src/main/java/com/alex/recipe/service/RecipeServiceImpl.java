package com.alex.recipe.service;

import com.alex.recipe.model.Recipe;
import com.alex.recipe.model.User;
import com.alex.recipe.repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    RecipeRepo recipeRepo;

    @Override
    public Recipe createRecipe(Recipe recipe, User user) {
        Recipe newRecipe = new Recipe();
        newRecipe.setTitle(recipe.getTitle());
        newRecipe.setImage(recipe.getImage());
        newRecipe.setDescription(recipe.getDescription());
        newRecipe.setUser(user);
        newRecipe.setCreatedAt(LocalDateTime.now());

        return recipeRepo.save(newRecipe);
    }

    @Override
    public Recipe findRecipeById(Long id) throws Exception {
        Optional<Recipe> recipe = recipeRepo.findById(id);
        if(recipe.isPresent()) return recipe.get();
        throw new Exception("Recipe with id:" + id + " not found");
    }

    @Override
    public void deleteRecipe(Long id) throws Exception {
        findRecipeById(id);
        recipeRepo.deleteById(id);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
        Recipe oldRecipe = findRecipeById(id);
        if (recipe.getTitle()!= null) oldRecipe.setTitle(recipe.getTitle());
        if (recipe.getDescription()!= null) oldRecipe.setDescription(recipe.getDescription());
        if (recipe.getImage()!= null) oldRecipe.setImage(recipe.getImage());

        return recipeRepo.save(oldRecipe);
    }

    @Override
    public List<Recipe> findAllRecipe() {
        return recipeRepo.findAll();
    }

    @Override
    public Recipe likeRecipe(Long id, User user) throws Exception {
        Recipe recipe = findRecipeById(id);
        if (recipe.getLikes().contains(user.getId())) {
            recipe.getLikes().remove((user.getId()));
        } else {
            recipe.getLikes().add(user.getId());
        }
        return recipeRepo.save(recipe);
    }
}
