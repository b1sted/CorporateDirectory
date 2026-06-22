package ru.basted.corporatedirectory.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN;

    @JsonCreator
    public static Role fromString(String value) {
        if (value == null) {
            return null;
        }

        String cleanValue = value.toUpperCase().trim();

        if (!cleanValue.startsWith("ROLE_")) {
            cleanValue = "ROLE_" + cleanValue;
        }

        return Role.valueOf(cleanValue);
    }
}
