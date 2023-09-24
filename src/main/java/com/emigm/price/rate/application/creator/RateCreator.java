package com.emigm.price.rate.application.creator;


import com.emigm.price.rate.domain.Rate;
import com.emigm.price.rate.domain.RateRepository;
import com.emigm.price.shared.domain.Injectable;

@Injectable
public class RateCreator {

    private final RateRepository repository;

    public RateCreator(RateRepository repository) {
        this.repository = repository;
    }

    public void create(RateCreatorCommand command) {
        Rate rate = Rate.create(command.startDate(), command.endDate(), command.priceListId(), command.productId(), command.priority(), command.price(), command.currency());

        repository.save(rate);

    }
}
