package org.test_task.products_categories.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.test_task.products_categories.dto.in.category.CategoryAddingDto;
import org.test_task.products_categories.dto.in.category.CategoryEditingDto;
import org.test_task.products_categories.entities.Category;
import org.test_task.products_categories.repositories.CategoryRepository;
import org.test_task.products_categories.services.interfaces.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public Category findById(Integer id) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }

    @Override
    public Category add(CategoryAddingDto addingDto, BindingResult bindingResult) {
        return null;
    }

    @Override
    public Category edit(CategoryEditingDto editingDto, BindingResult bindingResult) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

}
