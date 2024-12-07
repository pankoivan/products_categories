package org.test_task.products_categories.dto.in.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public abstract class ProductSavingDto {

    @JsonProperty("name")
    @NotBlank(message = "Обязательное поле")
    @Size(min = 6, max = 32, message = "От 6 до 32 символов")
    private String name;

    @JsonProperty("description")
    @NotBlank(message = "Обязательное поле")
    @Size(min = 16, max = 128, message = "От 16 до 128 символов")
    private String description;

    @JsonProperty("price")
    @NotNull(message = "Обязательное поле")
    @Min(value = 0, message = "Минимум 0")
    private Double price;

    public record EncodedBase64Image(String image, String extension) {

    }

}
