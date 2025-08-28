package com.Recipe.API.Recipe.API.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "recipes")
public class Recipe {

        @
                Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String cuisine;
        private String title;
        private Float rating;
        private Integer prepTime;
        private Integer cookTime;
        private Integer totalTime;

        @Column(columnDefinition = "TEXT")
        private String description;

        @Column(columnDefinition = "json")
        private String nutrients;

        private String serves;

    public Recipe(Long id, String cuisine, String title, Float rating, Integer prepTime, Integer cookTime, Integer totalTime, String description, String nutrients, String serves) {
        this.id = id;
        this.cuisine = cuisine;
        this.title = title;
        this.rating = rating;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.totalTime = totalTime;
        this.description = description;
        this.nutrients = nutrients;
        this.serves = serves;
    }

    public Recipe() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) && Objects.equals(cuisine, recipe.cuisine) && Objects.equals(title, recipe.title) && Objects.equals(rating, recipe.rating) && Objects.equals(prepTime, recipe.prepTime) && Objects.equals(cookTime, recipe.cookTime) && Objects.equals(totalTime, recipe.totalTime) && Objects.equals(description, recipe.description) && Objects.equals(nutrients, recipe.nutrients) && Objects.equals(serves, recipe.serves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cuisine, title, rating, prepTime, cookTime, totalTime, description, nutrients, serves);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", cuisine='" + cuisine + '\'' +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", prepTime=" + prepTime +
                ", cookTime=" + cookTime +
                ", totalTime=" + totalTime +
                ", description='" + description + '\'' +
                ", nutrients='" + nutrients + '\'' +
                ", serves='" + serves + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNutrients() {
        return nutrients;
    }

    public void setNutrients(String nutrients) {
        this.nutrients = nutrients;
    }

    public String getServes() {
        return serves;
    }

    public void setServes(String serves) {
        this.serves = serves;
    }
}

