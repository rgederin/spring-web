package com.gederin.converter;

import com.gederin.command.CategoryCommand;
import com.gederin.model.Category;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;

import static java.util.Objects.isNull;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (isNull(source)) {
            return null;
        }

        Category category = new Category();

        category.setId(source.getId());
        category.setDescription(source.getDescription());

        return category;
    }
}