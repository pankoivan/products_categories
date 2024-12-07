package org.test_task.products_categories.dto.in.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductAddingDto extends ProductSavingDto {

    @JsonProperty("image")
    @NotNull(message = "Обязательное поле")
    private EncodedBase64Image encodedImage;

    @JsonProperty("categoryId")
    @NotNull(message = "Обязательное поле")
    private Integer categoryId;

}
