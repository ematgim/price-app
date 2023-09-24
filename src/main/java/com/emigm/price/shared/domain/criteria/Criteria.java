package com.emigm.price.shared.domain.criteria;

import java.util.Objects;
import java.util.Optional;

public class Criteria {

    private final Filters filters;
    private final Order order;
    private final Optional<Integer> limit;
    private final Optional<Integer> offset;
    private final Filters subFilters;

    public Criteria(Filters filters) {
        this.filters = filters;
        this.order = Order.none();
        this.limit = Optional.empty();
        this.offset = Optional.empty();
        this.subFilters = Filters.none();
    }

    public Criteria(
            Filters filters,
            Filters subFilters,
            Order order,
            Optional<Integer> limit,
            Optional<Integer> offset) {
        this.filters = filters;
        this.subFilters = subFilters;
        this.order = order;
        this.limit = limit;
        this.offset = offset;
    }

    public Criteria(Filters filters, Order order, Optional<Integer> limit, Optional<Integer> offset) {
        this.filters = filters;
        this.order = order;
        this.limit = limit;
        this.offset = offset;
        this.subFilters = Filters.none();
    }

    public Criteria(Filters filters, Optional<Integer> limit, Optional<Integer> offset) {
        this.filters = filters;
        this.order = Order.none();
        this.limit = limit;
        this.offset = offset;
        this.subFilters = Filters.none();
    }

    public Criteria(Filters filters, Filters subFilters) {
        this.filters = filters;
        this.subFilters = subFilters;
        this.order = Order.none();
        this.limit = Optional.empty();
        this.offset = Optional.empty();
    }

    public Criteria(Filters filters, Order order) {
        this.filters = filters;
        this.order = order;
        this.limit = Optional.empty();
        this.offset = Optional.empty();
        this.subFilters = Filters.none();
    }

    public Filters filters() {
        return filters;
    }

    public Order order() {
        return order;
    }

    public Optional<Integer> limit() {
        return limit;
    }

    public Optional<Integer> offset() {
        return offset;
    }

    public boolean hasFilters() {
        return filters.filters().size() > 0;
    }

    public boolean hasSubFilters() {
        return filters.filters().size() > 0;
    }

    public Filters subFilters() {
        return subFilters;
    }

    public String serialize() {
        return String.format(
                "%s~~%s~~%s~~%s",
                filters.serialize(), order.serialize(), offset.orElse(0), limit.orElse(0));
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Criteria criteria = (Criteria) o;
        return Objects.equals(filters, criteria.filters)
                && Objects.equals(order, criteria.order)
                && Objects.equals(limit, criteria.limit)
                && Objects.equals(offset, criteria.offset)
                && Objects.equals(subFilters, criteria.subFilters);
    }

    @Override
    public int hashCode() {

        return Objects.hash(filters, order, limit, offset, subFilters);
    }

    @Override
    public String toString() {

        return "Criteria{"
                + "filters="
                + filters
                + ", order="
                + order
                + ", limit="
                + limit
                + ", offset="
                + offset
                + ", subFilters="
                + subFilters
                + '}';
    }
}
