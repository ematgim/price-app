package com.emigm.price.shared.domain.criteria;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class Filter {
    private final FilterField field;
    private final FilterOperator operator;
    private final FilterValue value;
    private final FilterType type;

    public Filter(FilterField field, FilterOperator operator, FilterValue value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
        this.type = FilterType.AND;
    }

    public Filter(FilterField field, FilterOperator operator, FilterValue value, FilterType type) {
        this.field = field;
        this.operator = operator;
        this.value = value;
        this.type = type;
    }

    public static Filter create(String field, String operator, Serializable value) {
        return new Filter(
                new FilterField(field),
                FilterOperator.fromValue(operator.toUpperCase()),
                new FilterValue(value),
                FilterType.AND);
    }

    public static Filter create(String field, String operator, String value, String type) {
        return new Filter(
                new FilterField(field),
                FilterOperator.fromValue(operator.toUpperCase()),
                new FilterValue(value),
                FilterType.fromValue(type.toUpperCase()));
    }

    public static Filter fromValues(HashMap<String, String> values) {
        return new Filter(
                new FilterField(values.get("field")),
                FilterOperator.fromValue(values.get("operator").toUpperCase()),
                new FilterValue(values.get("value")),
                FilterType.fromValue(values.get("type")));
    }

    public FilterField field() {
        return field;
    }

    public String fieldValue() {
        return field.value();
    }

    public FilterOperator operator() {
        return operator;
    }

    public FilterValue value() {
        return value;
    }

    public Serializable valueValue() {
        return value.value();
    }

    public FilterType type() {
        return type;
    }

    public String serialize() {
        return String.format("%s.%s.%s", field.value(), operator.value(), value.value());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return Objects.equals(field, filter.field)
                && operator == filter.operator
                && Objects.equals(value, filter.value)
                && type == filter.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, operator, value, type);
    }
}
