package com.gederin.controller;

import com.gederin.command.RecipeCommand;
import com.gederin.service.RecipeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class RecipeController {
    private RecipeService recipeService;

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findRecipeById(new Long(id)));

        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));

        return  "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting id: {}", id);

        recipeService.deleteRecipeById(Long.valueOf(id));

        return "redirect:/";
    }
}
