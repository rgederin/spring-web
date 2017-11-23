package com.gederin.command;

import com.gederin.model.Difficulty;

import org.hibernate.validator.constraints.URL;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;

    private @NotBlank @Size(min = 3, max = 255) String description;

    private @Min(1) @Max(999) Integer prepTime;

    private @Min(1) @Max(999) Integer cookTime;

    private @Min(1) @Max(100) Integer servings;
    private String source;

    private @URL String url;

    private @NotBlank String directions;

    private final Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
    private Difficulty difficulty;
    private NotesCommand notes;
    private final Set<CategoryCommand> categories = new HashSet<>();
}