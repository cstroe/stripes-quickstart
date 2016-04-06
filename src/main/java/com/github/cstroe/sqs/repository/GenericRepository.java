package com.github.cstroe.sqs.repository;

import com.github.cstroe.sqs.hibernate.HibernateSessionUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T,D> {
    Optional<T> findById(long id);
    void create(D dao);
    List<T> findAll();

    default Session getSession() {
        return HibernateSessionUtil.getCurrentSession();
    }
}
