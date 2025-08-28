package com.Recipe.API.Recipe.API.Controller;

import com.Recipe.API.Recipe.API.Entity.Recipe;
import com.Recipe.API.Recipe.API.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @PostMapping("/createrecipe")
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    // Endpoint 1: Get All Recipes (Paginated)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllRecipes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {

        Page<Recipe> recipesPage = recipeService.getAllRecipes(page, limit);

        Map<String, Object> response = new HashMap<>();
        response.put("page", page);
        response.put("limit", limit);
        response.put("total", recipesPage.getTotalElements());
        response.put("data", recipesPage.getContent());

        return ResponseEntity.ok(response);
    }

    // Endpoint 2: Search Recipes
    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipes(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) String calories,
            @RequestParam(required = false) String totalTime,
            @RequestParam(required = false) String rating) {

        List<Recipe> results = recipeService.searchRecipes(title, cuisine, calories, totalTime, rating);
        return ResponseEntity.ok(results);
    }
    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {
        return recipeService.getAllRecipes(1, Integer.MAX_VALUE) // Optional: you can create a service method findById
                .stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
