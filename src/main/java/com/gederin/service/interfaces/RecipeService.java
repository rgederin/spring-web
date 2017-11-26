package com.gederin.service.interfaces;

import com.gederin.command.RecipeCommand;
import com.gederin.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findRecipeById(Long id);

    RecipeCommand findRecipeCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteRecipeById(Long idToDelete);
}
