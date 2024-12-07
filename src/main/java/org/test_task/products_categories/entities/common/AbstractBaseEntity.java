package org.test_task.products_categories.entities.common;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = "id")
@SuperBuilder
public abstract class AbstractBaseEntity implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Override
    public boolean equals(Object o) {
        return (o == this) ||
                (o != null && o.getClass().equals(getClass()) &&
                        id != null &&
                        id.equals(((AbstractBaseEntity) o).getId())
                );
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
