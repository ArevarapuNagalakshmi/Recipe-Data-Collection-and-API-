package com.Recipe.API.Recipe.API.Util;

import com.Recipe.API.Recipe.API.Entity.Recipe;
import com.Recipe.API.Recipe.API.Repository.RecipeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class JsonParserUtil {

    @Autowired
    private RecipeRepository recipeRepository;

    public static List<Recipe> parseRecipesFromJson(String filePath) {
        return List.of();
    }

    public void importRecipes() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Recipe[] recipes = mapper.readValue(new File("src/main/resources/data/recipes.json"), Recipe[].class);

        for (Recipe r : recipes) {
            // ✅ For Double/Float fields (NaN possible)
            if (r.getRating() != null && Double.isNaN(r.getRating())) {
                r.setRating(null);
            }

            // ✅ For Integer fields (just check null, no isNaN)
            if (r.getPrepTime() != null && r.getPrepTime() < 0) {
                r.setPrepTime(null); // or handle invalid as needed
            }
            if (r.getCookTime() != null && r.getCookTime() < 0) {
                r.setCookTime(null);
            }
            if (r.getTotalTime() != null && r.getTotalTime() < 0) {
                r.setTotalTime(null);
            }

            recipeRepository.save(r);
        }
    }
}
