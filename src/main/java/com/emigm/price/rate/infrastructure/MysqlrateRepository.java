package com.emigm.price.rate.infrastructure;

import com.emigm.price.rate.domain.Rate;
import com.emigm.price.rate.domain.RateRepository;
import com.emigm.price.shared.domain.Injectable;
import com.emigm.price.shared.domain.criteria.Criteria;
import com.emigm.price.shared.domain.value_objects.InternalId;
import com.emigm.price.shared.infrastructure.persistence.hibernate.HibernateRepository;
import com.fasterxml.jackson.core.PrettyPrinter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Primary
@Injectable
public class MysqlrateRepository extends HibernateRepository<Rate> implements RateRepository {

    public MysqlrateRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Rate.class);
    }


    @Override
    public void save(Rate rate) {
        persist(rate);
    }

    @Override
    public List<Rate> findBetwenDateAndProductIdOrderByPriorityAsc(LocalDateTime date, Integer productId) {
        String sql = "SELECT r.id AS id, r.start_date as start_date,r.end_date AS end_date, r.price_list_id AS price_list_id, r.product_id as product_id, priority as priority, price as price, currency as currency "
                + " FROM  rate r "
                + " WHERE date(:date) between r.start_date " +
                "and r.end_date and r.product_id = :productId ORDER BY r.priority ASC";
        List<Map<String, Serializable>> results = executeSQLQueryWithParams(sql, new HashMap<>() {{
            put("date", date.toString()
            );
            put("productId", productId);
        }});

        return results.stream().map(Rate::fromPrimitives).collect(Collectors.toList());
    }

    @Override
    public List<Rate> match(Criteria criteria) {
        return byCriteria(criteria);
    }

    @Override
    public void remove(Rate rate) {


        String sql = "DELETE FROM Rate r WHERE r.id=:id ";
        executeSQLWithParams(sql, new HashMap<>() {{
            put("id", new InternalId(rate.idValue())
            );
        }});
    }
}
