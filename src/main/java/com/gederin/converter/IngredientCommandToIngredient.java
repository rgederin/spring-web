package com.gederin.converter;

import com.gederin.command.IngredientCommand;
import com.gederin.model.Ingredient;
import com.gederin.model.Recipe;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (Objects.isNull(source)) {
            return null;
        }

        Ingredient ingredient = new Ingredient();

        ingredient.setId(source.getId());

        if (Objects.nonNull(source.getRecipeId())) {
            Recipe recipe = new Recipe();
            recipe.setId(source.getRecipeId());
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(uomConverter.convert(source.getUom()));

        return ingredient;
    }
}
