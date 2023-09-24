package com.emigm.price.rate.domain;

import com.emigm.price.shared.domain.criteria.Criteria;

import java.time.LocalDateTime;
import java.util.List;

public interface RateRepository {


    void save(Rate rate);

    List<Rate> findBetwenDateAndProductIdOrderByPriorityAsc(LocalDateTime date, Integer productId);

    List<Rate> match(Criteria criteria);

    void remove(Rate rate);
}
