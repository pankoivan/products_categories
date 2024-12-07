package org.test_task.products_categories.services.interfaces;

import org.test_task.products_categories.dto.in.product.ProductAddingDto;
import org.test_task.products_categories.dto.in.product.ProductEditingDto;
import org.test_task.products_categories.entities.Product;
import org.test_task.products_categories.services.common.BaseService;

import java.util.Collection;

public interface ProductService extends BaseService<Product, ProductAddingDto, ProductEditingDto> {

    void makeInactive(Collection<Product> products);

}
