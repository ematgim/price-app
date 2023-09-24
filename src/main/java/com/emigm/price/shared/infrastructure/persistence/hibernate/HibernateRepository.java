package com.emigm.price.shared.infrastructure.persistence.hibernate;

import com.emigm.price.shared.domain.criteria.Criteria;
import com.emigm.price.shared.domain.value_objects.ValueObject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
public abstract class HibernateRepository<T> {
    protected final SessionFactory sessionFactory;
    protected final Class<T> aggregateClass;
    protected final HibernateCriteriaConverter criteriaConverter;

    public HibernateRepository(SessionFactory sessionFactory,
                               Class<T> aggregateClass) {
        this.sessionFactory = sessionFactory;
        this.aggregateClass = aggregateClass;
        this.criteriaConverter = new HibernateCriteriaConverter<T>(sessionFactory.getCriteriaBuilder());
    }

    protected void persist(T entity) {
        Session session = getSession();
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
        session.close();
        //session.flush();
        //getSession().clear();
    }

    protected Optional<T> byId(ValueObject id) {
        return Optional.ofNullable(getSession().byId(aggregateClass).load(id));
    }

    protected List<T> byCriteria(Criteria criteria) {
        CriteriaQuery<T> hibernateCriteria = criteriaConverter.convert(criteria,
                aggregateClass);

        Query query = getSession().createQuery(hibernateCriteria);

        query.setFirstResult(criteria.offset().orElse(0));
        query.setMaxResults(criteria.limit().orElse(100000));

        return query.getResultList();
    }

    protected Integer countByCriteria(Criteria criteria) {
        CriteriaQuery<Long> countQuery = criteriaConverter.convertToCountQuery(criteria,
                aggregateClass);
        Long count = getSession().createQuery(countQuery).getSingleResult();
        return count.intValue();
    }

    protected List<T> all() {
        CriteriaQuery<T> criteria = sessionFactory.getCriteriaBuilder().createQuery(aggregateClass);

        criteria.from(aggregateClass);

        return getSession().createQuery(criteria).getResultList();
    }

    protected Integer count() {
        CriteriaQuery<T> criteria = sessionFactory.getCriteriaBuilder().createQuery(aggregateClass);

        criteria.from(aggregateClass);

        return getSession().createQuery(criteria).getFetchSize();
    }

    protected Boolean existsByCriteria(Criteria criteria) {
        CriteriaQuery<T> hibernateCriteria = criteriaConverter.convert(criteria,
                aggregateClass);
        return getSession()
                .createQuery(hibernateCriteria)
                .uniqueResultOptional().isPresent();
    }

    protected void remove(T entity) {
        Session session = getSession();
        session.remove(entity);

    }

    protected List executeSQLQueryWithParams(String sqlQuery,
                                             Map<String, Serializable> params) {

        Query query = getSession()
                .createSQLQuery(sqlQuery)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        params.forEach((paramName, paramValue) -> query.setParameter(paramName,
                paramValue));

        return query.getResultList();
    }

    protected void executeSQLWithParams(String sqlQuery,
                                             Map<String, Serializable> params) {
        Session session = getSession();
        session.beginTransaction();
        Query query = session
                .createQuery(sqlQuery);

        params.forEach((paramName, paramValue) -> query.setParameter(paramName,
                paramValue));
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    protected Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            return sessionFactory.openSession();
        }
    }
}
