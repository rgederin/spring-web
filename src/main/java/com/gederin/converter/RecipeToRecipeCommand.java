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
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{

    private final CategoryToCategoryCommand categoryConveter;

    private final IngredientToIngredientCommand ingredientConverter;

    private final NotesToNotesCommand notesConverter;

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (Objects.isNull(source)) {
            return null;
        }

        RecipeCommand command = new RecipeCommand();

        command.setId(source.getId());
        command.setCookTime(source.getCookTime());
        command.setPrepTime(source.getPrepTime());
        command.setDescription(source.getDescription());
        command.setDifficulty(source.getDifficulty());
        command.setDirections(source.getDirections());
        command.setServings(source.getServings());
        command.setSource(source.getSource());
        command.setUrl(source.getUrl());
        command.setImage(source.getImage());
        command.setNotes(notesConverter.convert(source.getNotes()));

        CollectionUtils.emptyIfNull(source.getCategories())
                .forEach(category -> command.getCategories().add(categoryConveter.convert(category)));

        CollectionUtils.emptyIfNull(source.getIngredients())
                .forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));


        return command;
    }
}
