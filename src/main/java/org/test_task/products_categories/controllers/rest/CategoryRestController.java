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
@PreAuthorize("isAuthenticated()")
public class CategoryRestController {

    private final CategoryService service;

    @GetMapping
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public List<Category> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public Category findById(@PathVariable("id") String pathId) {
        return service.findById(service.validateAndParsePathId(pathId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Category add(@RequestBody @Valid CategorySavingDto savingDto, BindingResult bindingResult) {
        return service.add(savingDto, bindingResult);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Category edit(@PathVariable("id") String pathId, @RequestBody @Valid CategorySavingDto savingDto, BindingResult bindingResult) {
        return service.edit(service.validateAndParsePathId(pathId), savingDto, bindingResult);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable("id") String pathId) {
        service.deleteById(service.validateAndParsePathId(pathId));
    }

}
