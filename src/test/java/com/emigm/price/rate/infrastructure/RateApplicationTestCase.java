package com.emigm.price.rate.infrastructure;

import com.emigm.price.rate.application.RateSearcherResponse;
import com.emigm.price.rate.domain.Rate;
import com.emigm.price.rate.domain.RateMother;
import com.emigm.price.rate.domain.RateRepository;
import com.emigm.price.shared.domain.criteria.Criteria;
import com.emigm.price.shared.domain.criteria.Filters;
import com.emigm.price.shared.domain.criteria.Order;
import com.emigm.price.shared.infrastructure.ApplicationTestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class RateApplicationTestCase extends ApplicationTestCase {

    @Autowired
    protected RateRepository rateRepository;
    @BeforeEach
    void setUp() throws Exception {



    }

    @AfterEach
    void tearDown() {
        List<Rate> rates = rateRepository.match(new Criteria(Filters.none(), Order.none()));

        rates.forEach(rate -> rateRepository.remove(rate));
    }

    protected String buildValidRateBody(String endDate, String startDate, String priceListId, String productId, String priority, String price, String currency){

        return String.format("{\n" +
                "    \"endDate\": \"%s\",\n" +
                "    \"startDate\": \"%s\",\n" +
                "    \"priceListId\":%s,\n" +
                "    \"productId\":%s,\n" +
                "    \"priority\":%s,\n" +
                "    \"price\":%s,\n" +
                "    \"currency\":\"EUR\"\n" +
                "\n" +
                "}",endDate,startDate,priceListId,productId,priority,price,currency);

    }


    protected String buildRateRespose(Rate rate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");


        return String.format("{\n" +
                "    \"productId\": %s,\n" +
                "    \"endDate\": \"%s\",\n" +
                "    \"price\": %s,\n" +
                "    \"rateId\": %s,\n" +
                "    \"startDate\": \"%s\"\n" +
                "}",rate.productIdValue(),formatter.format(rate.endDateValue()) , rate.priceValue(),rate.idValue(), formatter.format(rate.startDateValue()) );



    }

    protected Rate givenAnActiveRateWithPriorityAndProductId(Integer priority, Integer productId) {
        Rate rate = RateMother.randomWithPriorityAndProductId(priority,productId);
        rateRepository.save(rate);
        return rate;
    }

    protected Rate givenAnExpiredDateRate() {
        Rate rate = RateMother.expired();
        rateRepository.save(rate);
        return rate;
    }
}
