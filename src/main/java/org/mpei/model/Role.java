package org.mpei.model;

public enum Role {
    ROLE_USER("Пользователь"),
    ROLE_ADMIN("Админ");
    private final String displayValue;

    Role(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
