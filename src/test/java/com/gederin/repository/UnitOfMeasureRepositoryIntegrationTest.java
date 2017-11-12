package com.gederin.repository;

import com.gederin.model.UnitOfMeasure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIntegrationTest {

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void shouldFindUOMByDescription(){
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Cup");

        assertThat(unitOfMeasure
                .orElseThrow(() -> new RuntimeException("Could not find uom"))
                .getDescription(), equalTo ("Cup"));
    }
}