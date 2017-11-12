package com.gederin.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeRepositoryIntegrationTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void shouldReturnFalsenWhenNotFoundRecipe() {
        assertThat(recipeRepository.findById(1L).isPresent(), equalTo(false));
    }
}