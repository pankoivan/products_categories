package org.test_task.products_categories.services.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.test_task.products_categories.dto.CategorySavingDto;
import org.test_task.products_categories.entities.Category;
import org.test_task.products_categories.exceptions.EntityNotFoundException;
import org.test_task.products_categories.repositories.CategoryRepository;
import org.test_task.products_categories.services.interfaces.CategoryService;
import org.test_task.products_categories.services.interfaces.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private ProductService productService;

    @Override
    public Category findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Категория с id \"%s\" не найдена"));
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category add(CategorySavingDto savingDto, BindingResult bindingResult) {
        validateBindingResult(bindingResult);
        return repository.save(
                Category
                        .builder()
                        .name(savingDto.getName())
                        .description(savingDto.getDescription())
                        .build()
        );
    }

    @Override
    public Category edit(Integer id, CategorySavingDto savingDto, BindingResult bindingResult) {
        validateBindingResult(bindingResult);
        Category category = findById(id);
        category.setName(savingDto.getName());
        category.setDescription(savingDto.getDescription());
        return repository.save(category);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Category category = findById(id);
        repository.deleteById(category.getId());
        productService.makeInactive(category.getProducts());
    }

}
