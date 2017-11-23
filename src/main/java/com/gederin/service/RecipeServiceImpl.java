package com.gederin.service;

import com.gederin.command.RecipeCommand;
import com.gederin.converter.RecipeCommandToRecipe;
import com.gederin.converter.RecipeToRecipeCommand;
import com.gederin.exception.NotFoundException;
import com.gederin.model.Recipe;
import com.gederin.repository.RecipeRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    private final RecipeCommandToRecipe recipeCommandToRecipe;

    private final RecipeToRecipeCommand recipeToRecipeCommand;

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
                .orElseThrow(() -> new NotFoundException("Recipe Not Found. For ID value: " + id));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);

        log.debug("Saved RecipeId:{}", savedRecipe.getId());

        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findRecipeCommandById(Long l) {
        return recipeToRecipeCommand.convert(findRecipeById(l));
    }

    @Override
    public void deleteRecipeById(Long idToDelete) {
        recipeRepository.deleteById(idToDelete);
    }
}
