package org.test_task.products_categories.services.interfaces;

import org.test_task.products_categories.dto.in.category.CategoryAddingDto;
import org.test_task.products_categories.dto.in.category.CategoryEditingDto;
import org.test_task.products_categories.entities.Category;
import org.test_task.products_categories.services.common.BaseService;

public interface CategoryService extends BaseService<Category, CategoryAddingDto, CategoryEditingDto> {

}
