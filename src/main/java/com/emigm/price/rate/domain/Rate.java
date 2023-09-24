package com.emigm.price.rate.domain;

import com.emigm.price.rate.domain.value_objects.*;
import com.emigm.price.rate.domain.value_objects.date_range.DateRangeDate;
import com.emigm.price.shared.domain.AggregateRoot;
import com.emigm.price.shared.domain.value_objects.InternalId;
import com.emigm.price.shared.infrastructure.Serializer;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Rate implements AggregateRoot {

    private final InternalId id;
    private final RateDateRange dateRange;
    private final RatePriceListId priceListId;
    private final RateProductId productId;
    private final RatePriority priority;
    private final RatePrice price;
    private final RateCurrency currency;


    public Rate() {
        this.id = new InternalId();
        this.dateRange = new RateDateRange();
        this.priceListId = new RatePriceListId();
        this.productId = new RateProductId();
        this.priority = new RatePriority();
        this.price = new RatePrice();
        this.currency = new RateCurrency();
    }

    public Rate(InternalId id, RateDateRange dateRange, RatePriceListId priceListId, RateProductId productId, RatePriority priority, RatePrice price, RateCurrency currency) {
        this.id = id;
        this.dateRange = dateRange;
        this.priceListId = priceListId;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }


    public static Rate create(LocalDateTime startDate, LocalDateTime endDate, Integer priceListId, Integer productId, Integer priority, Double price, String currency) {
        return new Rate(new InternalId(), new RateDateRange(new DateRangeDate(startDate), new DateRangeDate(endDate)), new RatePriceListId(priceListId), new RateProductId(productId), new RatePriority(priority), new RatePrice(price), new RateCurrency(currency));
    }

    public static Rate fromPrimitives(Map<String, Serializable> rawData) {
        return new Rate(new InternalId(((BigInteger) rawData.get("id")).longValue()), new RateDateRange(new DateRangeDate((Timestamp) rawData.get("start_date")), new DateRangeDate((Timestamp) rawData.get("end_date"))), new RatePriceListId((Integer) rawData.get("price_list_id")), new RateProductId((Integer) rawData.get("product_id")), new RatePriority((Integer) rawData.get("priority")), new RatePrice((Double) rawData.get("price")), new RateCurrency((String) rawData.get("currency")));
    }

    @Override
    public Map<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("id", id.value());
            put("start_date", dateRange.startDate());
            put("end_date", dateRange.endDate());
            put("price_list_id", priceListId.value());
            put("product_id", productId.value());
            put("priority", priority.value());
            put("price", price.value());
            put("currency", currency.value());


        }};
    }

    public InternalId id() {
        return id;
    }

    public RateDateRange dateRange() {
        return dateRange;
    }

    public RatePriceListId priceListId() {
        return priceListId;
    }

    public RateProductId productId() {
        return productId;
    }

    public RatePriority priority() {
        return priority;
    }

    public RatePrice price() {
        return price;
    }

    public RateCurrency currency() {
        return currency;
    }


    public Long idValue() {
        return id.value();
    }


    public LocalDateTime startDateValue() {
        return dateRange.startDateValue();
    }

    public LocalDateTime endDateValue() {
        return dateRange.endDateValue();
    }

    public Integer productIdValue() {
        return productId.value();
    }


    public Double priceValue() {
        return price.value();
    }

    public Integer priceListIdValue() {
        return priceListId().value();
    }

    public Integer priorityValue() {
        return priority().value();
    }

    public String currencyValue() {
        return currency().value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return Objects.equals(id, rate.id) && Objects.equals(dateRange, rate.dateRange) && Objects.equals(priceListId, rate.priceListId) && Objects.equals(productId, rate.productId) && Objects.equals(priority, rate.priority) && Objects.equals(price, rate.price) && Objects.equals(currency, rate.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateRange, priceListId, productId, priority, price, currency);
    }
}
