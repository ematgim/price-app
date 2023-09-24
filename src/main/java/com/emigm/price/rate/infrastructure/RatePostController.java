package com.emigm.price.rate.infrastructure;

import com.emigm.price.rate.application.creator.RateCreator;
import com.emigm.price.rate.application.creator.RateCreatorCommand;
import com.emigm.price.shared.infrastructure.Serializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;

@RestController
public class RatePostController {

    private final RateCreator rateCreator;

    public RatePostController(RateCreator rateCreator) {
        this.rateCreator = rateCreator;
    }


    @PostMapping("/api/rate/create")
    public ResponseEntity create(@RequestBody Map<String, Serializable> body) {


        rateCreator.create(new RateCreatorCommand(Serializer.decodeLocalDateTime((String) body.get("startDate")), Serializer.decodeLocalDateTime((String) body.get("endDate")), (Integer) body.get("priceListId"), (Integer) body.get("productId"), (Integer) body.get("priority"), (Double) body.get("price"), (String) body.get("currency")));

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
