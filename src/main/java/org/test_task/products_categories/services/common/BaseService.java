package org.test_task.products_categories.services.common;

import org.springframework.validation.BindingResult;

import java.util.List;

public interface BaseService<T, A, E> {

    T findById(Integer id);

    List<T> findAll();

    T add(A addingDto, BindingResult bindingResult);

    T edit(E editingDto, BindingResult bindingResult);

    void deleteById(Integer id);

}