package com.emigm.price.shared.domain.criteria;

public enum FilterType {
    AND("and"),
    OR("or");

    private final String type;

    FilterType(String type) {
        this.type = type;
    }

    public static FilterType fromValue(String value) {
        if ("OR".equalsIgnoreCase(value)) return FilterType.OR;
        return FilterType.AND;
    }

    public boolean isAggregation() {
        return this != AND;
    }

    public String value() {
        return type;
    }
}
