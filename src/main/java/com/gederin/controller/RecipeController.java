package com.gederin.controller;

import com.gederin.service.RecipeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RecipeController {
    private RecipeService recipeService;

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model) {

        model.addAttribute("recipe", recipeService.findRecipeById(Long.valueOf(id)));

        return "recipe/show";
    }
}
