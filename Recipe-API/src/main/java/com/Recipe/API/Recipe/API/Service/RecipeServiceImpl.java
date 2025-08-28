package com.Recipe.API.Recipe.API.Service;

import com.Recipe.API.Recipe.API.Entity.Recipe;
import com.Recipe.API.Recipe.API.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Page<Recipe> getAllRecipes(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("rating").descending());
        return recipeRepository.findAll(pageable);
    }

    @Override
    public List<Recipe> searchRecipes(String title, String cuisine, String calories, String totalTime, String rating) {

        Specification<Recipe> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }
            if (cuisine != null && !cuisine.isEmpty()) {
                predicates.add(cb.equal(root.get("cuisine"), cuisine));
            }
            // Calories, totalTime, rating filters can be parsed for >=, <=, = operators
            // Example for rating >= 4.5
            if (rating != null && rating.startsWith(">=")) {
                Float r = Float.parseFloat(rating.substring(2));
                predicates.add(cb.greaterThanOrEqualTo(root.get("rating"), r));
            } else if (rating != null && rating.startsWith("<=")) {
                Float r = Float.parseFloat(rating.substring(2));
                predicates.add(cb.lessThanOrEqualTo(root.get("rating"), r));
            }

            // Similar parsing can be done for totalTime and calories (if stored in nutrients JSON)
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return recipeRepository.findAll(spec);
    }
    @Override
    public Optional<Recipe> findById(Long id) {
        return recipeRepository.findById(id);
    }
}
