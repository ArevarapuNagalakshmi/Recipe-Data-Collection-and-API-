package com.Recipe.API.Recipe.API.Service;

import com.Recipe.API.Recipe.API.Entity.Recipe;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Page<Recipe> getAllRecipes(int page, int limit);
    List<Recipe> searchRecipes(String title, String cuisine, String calories, String totalTime, String rating);
    Recipe addRecipe(Recipe recipe);
    Optional<Recipe> findById(Long id);
}
