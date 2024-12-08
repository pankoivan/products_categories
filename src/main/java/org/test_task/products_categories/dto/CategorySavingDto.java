package org.test_task.products_categories.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategorySavingDto {

    @JsonProperty("name")
    @NotBlank(message = "Обязательное поле")
    @Size(min = 6, max = 32, message = "От 6 до 32 символов")
    private String name;

    @JsonProperty("description")
    @NotBlank(message = "Обязательное поле")
    @Size(min = 16, max = 128, message = "От 16 до 128 символов")
    private String description;

}
