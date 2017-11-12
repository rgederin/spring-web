package com.gederin.repository;

import com.gederin.model.Category;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryIntegrationTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void shouldFindCategoryBeDescription() {
        Optional<Category> category = categoryRepository.findByDescription("Mexican");

        assertThat(category
                .orElseThrow(() -> new RuntimeException("Could not find category"))
                .getDescription(), equalTo("Mexican"));
    }
}