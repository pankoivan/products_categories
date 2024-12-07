package org.test_task.products_categories.dto.in.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductEditingDto extends ProductSavingDto {

    @JsonProperty("id")
    @NotNull(message = "Обязательное поле")
    private Integer id;

    @JsonProperty("image")
    private EncodedBase64Image encodedImage;

    @JsonProperty("categoryId")
    private Integer categoryId;

    @JsonProperty("status")
    private Boolean status;

}
