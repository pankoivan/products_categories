package org.test_task.products_categories.services.interfaces;

import org.test_task.products_categories.dto.ProductSavingDto;
import org.test_task.products_categories.entities.Product;
import org.test_task.products_categories.services.common.BaseService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ProductService extends BaseService<Product, ProductSavingDto> {

    List<Product> findAll(Map<String, String> params);

    void makeInactive(Collection<Product> products);

}
