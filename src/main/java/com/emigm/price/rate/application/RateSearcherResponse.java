package com.emigm.price.rate.application;

import com.emigm.price.rate.domain.Rate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class RateSearcherResponse {


    private Integer productId;
    private Long rateId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;


    public RateSearcherResponse() {
    }

    public RateSearcherResponse(Integer productId, Long rateId, LocalDateTime startDate, LocalDateTime endDate, Double price) {
        this.productId = productId;
        this.rateId = rateId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public static RateSearcherResponse fromAggregate(Rate rate) {
        return new RateSearcherResponse(rate.productIdValue(), rate.idValue(), rate.startDateValue(), rate.endDateValue(), rate.priceValue());
    }

    public Integer productId() {
        return productId;
    }

    public Long rateId() {
        return rateId;
    }

    public LocalDateTime startDate() {
        return startDate;
    }

    public LocalDateTime endDate() {
        return endDate;
    }

    public Double price() {
        return price;
    }


    public Map<String, Serializable> toPrimitives(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");


        return new HashMap<>(){{
            put("productId",productId());
            put("rateId",rateId());
            put("startDate",formatter.format(startDate()));
            put("endDate",formatter.format(endDate()));
            put("price",price());
        }};



    }
}
