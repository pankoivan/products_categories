package org.test_task.products_categories.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.test_task.products_categories.entities.common.AbstractBaseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "category", callSuper = true)
@SuperBuilder
public class Product extends AbstractBaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public String getFormattedCreationDate() {
        return DateTimeFormatter.ofPattern("dd MMM yyyy в HH:mm:ss").format(creationDate);
    }

}
