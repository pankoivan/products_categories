package org.test_task.products_categories.controllers.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.test_task.products_categories.dto.CategorySavingDto;
import org.test_task.products_categories.entities.Category;
import org.test_task.products_categories.services.interfaces.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
@PreAuthorize("permitAll()")
public class CategoryRestController {

    private final CategoryService service;

    @GetMapping
    public List<Category> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable("id") String pathId) {
        return service.findById(service.validateAndParsePathId(pathId));
    }

    @PostMapping
    public void add(@RequestBody @Valid CategorySavingDto savingDto, BindingResult bindingResult) {
        service.add(savingDto, bindingResult);
    }

    @PutMapping("/{id}")
    public void edit(@PathVariable("id") String pathId, @RequestBody @Valid CategorySavingDto savingDto, BindingResult bindingResult) {
        service.edit(service.validateAndParsePathId(pathId), savingDto, bindingResult);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String pathId) {
        service.deleteById(service.validateAndParsePathId(pathId));
    }

}
