package com.gederin.service;

import com.gederin.repository.RecipeRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    private RecipeService recipeService;

    @Before
    public void setUp(){
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void shouldCallRepositoryOnlyOnceWhenExecuteGetRecipes(){
        recipeService.getRecipes();

        verify(recipeRepository, times(1)).findAll();
    }
}