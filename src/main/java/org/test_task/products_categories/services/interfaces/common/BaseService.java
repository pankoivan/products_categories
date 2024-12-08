package org.test_task.products_categories.services.interfaces.common;

import org.springframework.validation.BindingResult;

import java.util.List;

public interface BaseService<T, S> extends ValidationService {

    T findById(Integer id);

    List<T> findAll();

    T add(S savingDto, BindingResult bindingResult);

    T edit(Integer id, S savingDto, BindingResult bindingResult);

    void deleteById(Integer id);

}
