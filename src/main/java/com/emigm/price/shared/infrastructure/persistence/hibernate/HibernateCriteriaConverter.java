package com.emigm.price.shared.infrastructure.persistence.hibernate;


import com.emigm.price.shared.domain.criteria.Criteria;
import com.emigm.price.shared.domain.criteria.Filter;
import com.emigm.price.shared.domain.criteria.FilterOperator;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public final class HibernateCriteriaConverter<T> {

    private final CriteriaBuilder builder;
    private final HashMap<FilterOperator, BiFunction<Filter, Root<T>, Predicate>> predicateTransformers =
            new HashMap<FilterOperator, BiFunction<Filter, Root<T>, Predicate>>() {{
                put(FilterOperator.EQUAL,
                        HibernateCriteriaConverter.this::equalsPredicateTransformer);
                put(FilterOperator.NOT_EQUAL,
                        HibernateCriteriaConverter.this::notEqualsPredicateTransformer);
                put(FilterOperator.GT,
                        HibernateCriteriaConverter.this::greaterThanPredicateTransformer);
                put(FilterOperator.LT,
                        HibernateCriteriaConverter.this::lowerThanPredicateTransformer);
                put(FilterOperator.CONTAINS,
                        HibernateCriteriaConverter.this::containsPredicateTransformer);
                put(FilterOperator.NOT_CONTAINS,
                        HibernateCriteriaConverter.this::notContainsPredicateTransformer);
            }};

    public HibernateCriteriaConverter(CriteriaBuilder builder) {
        this.builder = builder;
    }

    public CriteriaQuery<T> convert(Criteria criteria,
                                    Class<T> aggregateClass) {
        CriteriaQuery<T> hibernateCriteria = builder.createQuery(aggregateClass);
        Root<T> root = hibernateCriteria.from(aggregateClass);

        hibernateCriteria.where(formatPredicates(criteria.filters().filters(),
                root));

        if (criteria.order().hasOrder()) {
            Path<Object> orderBy = root.get(criteria.order().orderBy().value());
            Order order = criteria.order().orderType().isAsc() ? builder.asc(orderBy) : builder.desc(orderBy);

            hibernateCriteria.orderBy(order);
        }

        return hibernateCriteria;
    }

    public CriteriaQuery<Long> convertToCountQuery(Criteria criteria,
                                                   Class<T> aggregateClass) {
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<T> root = countQuery.from(aggregateClass);

        countQuery.select(builder.count(root));
        countQuery.where(formatPredicates(criteria.filters().filters(),
                root));

        return countQuery;
    }


    private Predicate[] formatPredicates(List<Filter> filters,
                                         Root<T> root) {
        List<Predicate> predicates = filters.stream().map(filter -> formatPredicate(filter,
                root)).collect(Collectors.toList());

        Predicate[] predicatesArray = new Predicate[predicates.size()];
        predicatesArray = predicates.toArray(predicatesArray);

        return predicatesArray;
    }

    private Predicate formatPredicate(Filter filter,
                                      Root<T> root) {
        BiFunction<Filter, Root<T>, Predicate> transformer = predicateTransformers.get(filter.operator());

        return transformer.apply(filter,
                root);
    }

    private Predicate equalsPredicateTransformer(Filter filter,
                                                 Root<T> root) {
        return builder.equal(root.get(filter.field().value()).get("value"),
                filter.value().value());
    }

    private Predicate notEqualsPredicateTransformer(Filter filter,
                                                    Root<T> root) {
        return builder.notEqual(root.get(filter.field().value()).get("value"),
                filter.value().value());
    }

    private Predicate greaterThanPredicateTransformer(Filter filter,
                                                      Root<T> root) {



        List<String> nestedFields =new ArrayList<>(Arrays.asList( filter.field().value().split("\\."))) ;
        Path path = root.get(nestedFields.remove(0));
        for (String nestedField : nestedFields) {
            path = path.get(nestedField);
        }

        return builder.greaterThan(path,
                filter.value().value().toString());
    }

    private Predicate lowerThanPredicateTransformer(Filter filter,
                                                    Root<T> root) {


        List<String> nestedFields =new ArrayList<>(Arrays.asList( filter.field().value().split("\\."))) ;
        Path path = root.get(nestedFields.remove(0));
        for (String nestedField : nestedFields) {
            path = path.get(nestedField);
        }
        return builder.lessThan(path,
                filter.value().value().toString());
    }

    private Predicate containsPredicateTransformer(Filter filter,
                                                   Root<T> root) {
        return builder.like(root.get(filter.field().value()).get("value"),
                String.format("%%%s%%",
                        filter.value().value()));
    }

    private Predicate notContainsPredicateTransformer(Filter filter,
                                                      Root<T> root) {
        return builder.notLike(root.get(filter.field().value()).get("value"),
                String.format("%%%s%%",
                        filter.value().value()));
    }
}
