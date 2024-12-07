package org.test_task.products_categories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test_task.products_categories.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
