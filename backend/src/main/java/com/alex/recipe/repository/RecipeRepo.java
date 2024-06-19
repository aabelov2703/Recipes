package com.alex.recipe.repository;

import com.alex.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepo extends JpaRepository<Recipe, Long> {
}
