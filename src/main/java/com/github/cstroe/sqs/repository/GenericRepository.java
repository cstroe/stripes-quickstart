package com.github.cstroe.sqs.repository;

import com.github.cstroe.sqs.hibernate.HibernateSessionUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

/**
 * @param <MODEL> A type in our model.
 * @param <DAO> The DAO implementation of the model interface.
 */
public interface GenericRepository<MODEL, DAO extends MODEL> {
    Optional<DAO> findById(long id);
    void save(DAO dao);
    List<MODEL> findAll();

    default Session getSession() {
        return HibernateSessionUtil.getCurrentSession();
    }
}
