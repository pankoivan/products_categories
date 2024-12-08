package org.test_task.products_categories.controllers.mvc;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.test_task.products_categories.services.interfaces.CategoryService;

@Controller
@RequestMapping("/categories")
@AllArgsConstructor
@PreAuthorize("permitAll()")
public class CategoryMvcController {

    private final CategoryService service;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("categories", service.findAll());
        return "categories";
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable("id") String pathId) {
        model.addAttribute("category", service.findById(service.validateAndParsePathId(pathId)));
        return "category";
    }

}
