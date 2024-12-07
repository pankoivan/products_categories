package org.test_task.products_categories.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.test_task.products_categories.entities.common.AbstractBaseEntity;

import java.util.Set;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "products", callSuper = true)
@SuperBuilder
public class Category extends AbstractBaseEntity {

    private String name;

    private String description;

    private Set<Product> products;

}
