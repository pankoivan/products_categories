package org.test_task.products_categories.dto.in.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryEditingDto extends CategorySavingDto {

    @JsonProperty("id")
    @NotNull(message = "Обязательное поле")
    private Integer id;

}
