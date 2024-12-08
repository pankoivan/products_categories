package org.test_task.products_categories.controllers.mvc;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.test_task.products_categories.services.interfaces.CategoryService;
import org.test_task.products_categories.services.interfaces.ProductService;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
@PreAuthorize("permitAll()")
public class ProductMvcController {

    private final ProductService service;

    private final CategoryService categoryService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("products", service.findAll());
        return "main/product/products";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "main/product/product-add";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") String pathId) {
        model.addAttribute("product", service.findById(service.validateAndParsePathId(pathId)));
        model.addAttribute("categories", categoryService.findAll());
        return "main/product/product-edit";
    }

}
