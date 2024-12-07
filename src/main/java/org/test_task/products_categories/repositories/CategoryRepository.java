package org.test_task.products_categories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test_task.products_categories.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
