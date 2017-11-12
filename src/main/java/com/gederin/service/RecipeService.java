package com.gederin.service;

import com.gederin.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findRecipeById(Long id);
}
