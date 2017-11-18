package com.gederin.service;

import com.gederin.command.RecipeCommand;
import com.gederin.converter.RecipeCommandToRecipe;
import com.gederin.converter.RecipeToRecipeCommand;
import com.gederin.model.Recipe;
import com.gederin.repository.RecipeRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
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

    @Test
    public void getRecipeCommandByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand commandById = recipeService.findRecipeCommandById(1L);

        assertNotNull("Null recipe returned", commandById);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }



    @Test
    public void getRecipesTest() throws Exception {
        Recipe recipe = new Recipe();

        Set<Recipe> receipesData = new HashSet();
        receipesData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(receipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }

    @Test
    public void testDeleteById() throws Exception {

        //given
        Long idToDelete = Long.valueOf(2L);

        //when
        recipeService.deleteRecipeById(idToDelete);

        //no 'when', since method has void return type

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}