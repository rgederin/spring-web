package com.gederin.controller;

import com.gederin.command.RecipeCommand;
import com.gederin.service.interfaces.ImageService;
import com.gederin.service.interfaces.RecipeService;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));

        return "recipe/imageuploadform";
    }

    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(id));

        if (Objects.nonNull(recipeCommand.getImage())) {
            byte[] byteArray = new byte[recipeCommand.getImage().length];
            int i = 0;

            for (Byte wrappedByte : recipeCommand.getImage()) {
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");

            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
