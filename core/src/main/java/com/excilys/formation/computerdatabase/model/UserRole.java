package com.excilys.formation.computerdatabase.model;

public enum UserRole {
    ROLE_USER, ROLE_ADMIN;
    
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}