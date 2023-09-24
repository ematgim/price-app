package com.emigm.price.rate.application.creator;

import java.time.LocalDateTime;

public class RateCreatorCommand {

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Integer priceListId;
    private final Integer productId;
    private final Integer priority;
    private final Double price;
    private final String currency;


    public RateCreatorCommand(LocalDateTime startDate, LocalDateTime endDate, Integer priceListId, Integer productId, Integer priority, Double price, String currency) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceListId = priceListId;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    public LocalDateTime startDate() {
        return startDate;
    }

    public LocalDateTime endDate() {
        return endDate;
    }

    public Integer priceListId() {
        return priceListId;
    }

    public Integer productId() {
        return productId;
    }

    public Integer priority() {
        return priority;
    }

    public Double price() {
        return price;
    }

    public String currency() {
        return currency;
    }
}
