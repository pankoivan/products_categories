package org.test_task.products_categories.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.test_task.products_categories.entities.common.AbstractBaseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "category", callSuper = true)
@SuperBuilder
public class Product extends AbstractBaseEntity {

    private String name;

    private String description;

    private Double price;

    private String imageName;

    private LocalDateTime creationDate;

    private Boolean status;

    private Category category;

}
