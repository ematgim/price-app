package com.emigm.price.shared.domain.criteria;

public enum FilterOperator {
    EQUAL("="),
    NOT_EQUAL("!="),
    GT(">"),
    GTE(">="),
    LT("<"),
    LTE("<="),
    CONTAINS("CONTAINS"),
    NOT_CONTAINS("NOT_CONTAINS"),
    STARTS_WITH("STARTS_WITH");

    private final String operator;

    FilterOperator(String operator) {
        this.operator = operator;
    }

    public static FilterOperator fromValue(String value) {
        switch (value) {
            case "=":
                return FilterOperator.EQUAL;
            case "!=":
                return FilterOperator.NOT_EQUAL;
            case ">":
                return FilterOperator.GT;
            case ">=":
                return FilterOperator.GTE;
            case "<":
                return FilterOperator.LT;
            case "<=":
                return FilterOperator.LTE;
            case "CONTAINS":
                return FilterOperator.CONTAINS;
            case "NOT_CONTAINS":
                return FilterOperator.NOT_CONTAINS;
            case "STARTS_WITH":
                return FilterOperator.STARTS_WITH;
            default:
                return null;
        }
    }

    public boolean isPositive() {
        return this != NOT_EQUAL && this != NOT_CONTAINS;
    }

    public String value() {
        return operator;
    }
}
