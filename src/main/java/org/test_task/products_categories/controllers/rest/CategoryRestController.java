package org.test_task.products_categories.controllers.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.test_task.products_categories.dto.in.category.CategoryAddingDto;
import org.test_task.products_categories.dto.in.category.CategoryEditingDto;
import org.test_task.products_categories.entities.Category;
import org.test_task.products_categories.services.interfaces.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryRestController {

    private final CategoryService service;

    @GetMapping
    public List<Category> findAll() {
        return service.findAll();
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid CategoryAddingDto addingDto, BindingResult bindingResult) {
        service.add(addingDto, bindingResult);
    }

    @PostMapping("/edit")
    public void edit(@RequestBody @Valid CategoryEditingDto editingDto, BindingResult bindingResult) {
        service.edit(editingDto, bindingResult);
    }

    @PostMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") String pathId) {
        service.deleteById(service.validateAndParsePathId(pathId));
    }

}
