package org.test_task.products_categories.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.test_task.products_categories.dto.in.product.ProductAddingDto;
import org.test_task.products_categories.dto.in.product.ProductEditingDto;
import org.test_task.products_categories.entities.Product;
import org.test_task.products_categories.exceptions.EntityNotFoundException;
import org.test_task.products_categories.repositories.ProductRepository;
import org.test_task.products_categories.services.interfaces.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Product findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Продукт с id \"%s\" не найден"));
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Product add(ProductAddingDto addingDto, BindingResult bindingResult) {
        return null;
    }

    @Override
    public Product edit(ProductEditingDto editingDto, BindingResult bindingResult) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

}
