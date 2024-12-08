package org.test_task.products_categories.controllers.errors.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponseEntity {

    private Integer errorStatusCode;

    private boolean fieldErrors;

}
