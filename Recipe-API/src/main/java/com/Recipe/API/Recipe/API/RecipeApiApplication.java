package com.Recipe.API.Recipe.API;

import com.Recipe.API.Recipe.API.Entity.Recipe;
import com.Recipe.API.Recipe.API.Repository.RecipeRepository;
import com.Recipe.API.Recipe.API.Util.JsonParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RecipeApiApplication implements CommandLineRunner {

	@Autowired
	private RecipeRepository recipeRepository;

	public static void main(String[] args) {
		SpringApplication.run(RecipeApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Path to your JSON file
		String filePath = "src/main/resources/data/recipes.json";

		// Parse JSON into Recipe objects
		List<Recipe> recipes = JsonParserUtil.parseRecipesFromJson(filePath);

		if (!recipes.isEmpty()) {
			recipeRepository.saveAll(recipes);
			System.out.println("Imported " + recipes.size() + " recipes into the database.");
		} else {
			System.out.println("No recipes were imported (empty JSON or parsing issue).");
		}
	}
}
