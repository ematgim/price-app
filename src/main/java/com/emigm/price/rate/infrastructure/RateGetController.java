package com.emigm.price.rate.infrastructure;

import com.emigm.price.rate.application.RateSearcherResponse;
import com.emigm.price.rate.application.searcher.RateSearcher;
import com.emigm.price.rate.application.searcher.RateSearcherQuery;
import com.emigm.price.rate.domain.Rate;
import com.emigm.price.rate.domain.RateNotFound;
import com.emigm.price.shared.infrastructure.Serializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class RateGetController {


    private final RateSearcher rateSearcher;

    public RateGetController(RateSearcher rateSearcher) {
        this.rateSearcher = rateSearcher;
    }


    @GetMapping("/api/rate/search")
    public ResponseEntity search(@RequestParam Map<String,Serializable> params) {

        try {

            RateSearcherResponse response = rateSearcher.execute(new RateSearcherQuery(Integer.parseInt((String) params.get("productId")),params.containsKey("date")?Serializer.decodeLocalDateTime((String) params.get("date")): LocalDateTime.now()));
            return ResponseEntity.status(HttpStatus.OK).body(response.toPrimitives());
        } catch (RateNotFound e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }


    }



}
