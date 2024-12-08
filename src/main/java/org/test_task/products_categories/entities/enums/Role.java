package org.test_task.products_categories.entities.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Role implements GrantedAuthority {

    USER("Пользователь"),

    ADMIN("Администратор");

    private final String alias;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }

}
