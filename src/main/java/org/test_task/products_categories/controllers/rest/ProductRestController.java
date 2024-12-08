package org.test_task.products_categories.controllers.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.test_task.products_categories.dto.in.product.ProductAddingDto;
import org.test_task.products_categories.dto.in.product.ProductEditingDto;
import org.test_task.products_categories.entities.Product;
import org.test_task.products_categories.services.interfaces.ProductService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@PreAuthorize("permitAll()")
public class ProductRestController {

    private final ProductService service;

    @GetMapping
    public List<Product> findAll(Map<String, String> params) {
        return service.findAll(params);
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid ProductAddingDto addingDto, BindingResult bindingResult) {
        service.add(addingDto, bindingResult);
    }

    @PostMapping("/edit")
    public void edit(@RequestBody @Valid ProductEditingDto editingDto, BindingResult bindingResult) {
        service.edit(editingDto, bindingResult);
    }

    @PostMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") String pathId) {
        service.deleteById(service.validateAndParsePathId(pathId));
    }

}
