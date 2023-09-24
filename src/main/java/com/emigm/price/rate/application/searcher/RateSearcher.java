package com.emigm.price.rate.application.searcher;

import com.emigm.price.rate.application.RateSearcherResponse;
import com.emigm.price.rate.domain.Rate;
import com.emigm.price.rate.domain.RateNotFound;
import com.emigm.price.rate.domain.RateRepository;
import com.emigm.price.shared.domain.Injectable;

import java.util.List;

@Injectable
public class RateSearcher {

    private final RateRepository repository;

    public RateSearcher(RateRepository repository) {
        this.repository = repository;
    }

    public RateSearcherResponse execute(RateSearcherQuery query) {



        List<Rate> rates = repository.findBetwenDateAndProductIdOrderByPriorityAsc(query.dateValue(), query.productIdValue());

        if(rates.isEmpty()){
            throw new RateNotFound();
        }

        return RateSearcherResponse.fromAggregate(rates.get(0));

    }


}
