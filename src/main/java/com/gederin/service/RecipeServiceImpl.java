package com.gederin.service;

import com.gederin.model.Recipe;
import com.gederin.repository.RecipeRepository;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();

        recipeRepository.findAll()
                .iterator()
                .forEachRemaining(recipeSet::add);

        return recipeSet;
    }

    @Override
    public Recipe findRecipeById(Long id) {

        return recipeRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }
}
