package com.gederin.service;

import com.gederin.converter.RecipeCommandToRecipe;
import com.gederin.converter.RecipeToRecipeCommand;
import com.gederin.model.Recipe;
import com.gederin.repository.RecipeRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceImplTest {
    private static final Long RECIPE_ID = 1L;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private Recipe recipe;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    private RecipeService recipeService;

    @Before
    public void setUp() {
        when(recipeRepository.findById(RECIPE_ID)).thenReturn(Optional.of(recipe));
        when(recipe.getId()).thenReturn(RECIPE_ID);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void shouldCallRepositoryOnlyOnceWhenExecuteGetRecipes() {
        recipeService.getRecipes();

        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void shouldCallRepositoryOnlyOnceWhenExecuteGetRecipeById() {
        recipeService.findRecipeById(RECIPE_ID);

        verify(recipeRepository, times(1)).findById(RECIPE_ID);
    }

    @Test
    public void shouldCallReturnRecipeFromRepositoryWhenExecuteGetRecipeById() {
        recipeService.findRecipeById(RECIPE_ID);

        assertThat("Should be id 1", recipeService.findRecipeById(RECIPE_ID).getId(), equalTo(RECIPE_ID));
    }
}