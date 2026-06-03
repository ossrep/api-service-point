package com.ossrep.servicepoint.api;

public enum Roles {

    ADMIN("admin"),
    USER("user");

    private final String value;

    Roles(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static final String ADMIN_ROLE = "admin";
    public static final String USER_ROLE = "user";
}
