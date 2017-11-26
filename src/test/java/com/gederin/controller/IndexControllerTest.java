package com.gederin.controller;

import com.google.common.collect.ImmutableSet;

import com.gederin.model.Recipe;
import com.gederin.service.interfaces.RecipeService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    @Mock
    private Model model;

    @Mock
    private RecipeService recipeService;

    private IndexController indexController;

    @Before
    public void setUp() {
        indexController = new IndexController(recipeService);
    }

    @Test
    public void shouldCallServiceOnlyOnceWhenExecuteGetIndexPage() {
        indexController.getIndexPage(model);

        verify(recipeService, times(1)).getRecipes();
    }

    @Test
    public void shouldAddAttributeToModeOnlyOnceWhenExecuteGetRecipes() {
        indexController.getIndexPage(model);

        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
    }

    @Test
    public void shouldReturnIndexPageString() {
        assertThat("Should return index", indexController.getIndexPage(model), equalTo("index"));
    }

    @Test
    public void shouldAddInModelSetFromRecipeService() {
        prepareRecipesSetForTest();

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        indexController.getIndexPage(model);

        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        assertThat("Should have set with two elements", argumentCaptor.getValue().size(), equalTo(2));
    }

    private Set<Recipe> prepareRecipesSetForTest() {
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);

        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);

        Set<Recipe> recipes = ImmutableSet.of(recipe1, recipe2);
        when(recipeService.getRecipes()).thenReturn(recipes);

        return recipes;
    }
}