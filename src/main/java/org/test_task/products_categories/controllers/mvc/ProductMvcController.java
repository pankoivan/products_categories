package org.test_task.products_categories.controllers.mvc;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.test_task.products_categories.services.interfaces.CategoryService;
import org.test_task.products_categories.services.interfaces.ProductService;

import java.util.Map;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ProductMvcController {

    private final ProductService service;

    private final CategoryService categoryService;

    @GetMapping
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public String findAll(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("products", service.findAll(params));
        return "product-list";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String add(Model model) {
        model.addAttribute("title", "Создание товара");
        model.addAttribute("product", null);
        model.addAttribute("categories", categoryService.findAll());
        return "product-save";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String edit(Model model, @PathVariable("id") String pathId) {
        model.addAttribute("title", "Изменение товара");
        model.addAttribute("product", service.findById(service.validateAndParsePathId(pathId)));
        model.addAttribute("categories", categoryService.findAll());
        return "product-save";
    }

}
