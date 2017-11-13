package com.gederin.converter;

import com.gederin.command.RecipeCommand;
import com.gederin.model.Recipe;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Synchronized;

@Component
@AllArgsConstructor
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConveter;

    private final IngredientCommandToIngredient ingredientConverter;

    private final NotesCommandToNotes notesConverter;

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (Objects.isNull(source)) {
            return null;
        }

        Recipe recipe = new Recipe();

        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDescription(source.getDescription());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setNotes(notesConverter.convert(source.getNotes()));

        CollectionUtils.emptyIfNull(source.getCategories())
                .forEach(category -> recipe.getCategories().add(categoryConveter.convert(category)));


        CollectionUtils.emptyIfNull(source.getIngredients())
                .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));

        return recipe;
    }
}
