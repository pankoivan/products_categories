package org.test_task.products_categories.controllers.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.test_task.products_categories.dto.ProductSavingDto;
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
    public List<Product> findAll(@RequestParam Map<String, String> params) {
        System.out.println(params);
        return service.findAll(params);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") String pathId) {
        return service.findById(service.validateAndParsePathId(pathId));
    }

    @PostMapping
    public void add(@RequestBody @Valid ProductSavingDto savingDto, BindingResult bindingResult) {
        service.add(savingDto, bindingResult);
    }

    @PutMapping("/{id}")
    public void edit(@PathVariable("id") String pathId, @RequestBody @Valid ProductSavingDto savingDto, BindingResult bindingResult) {
        service.edit(service.validateAndParsePathId(pathId), savingDto, bindingResult);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String pathId) {
        service.deleteById(service.validateAndParsePathId(pathId));
    }

}
