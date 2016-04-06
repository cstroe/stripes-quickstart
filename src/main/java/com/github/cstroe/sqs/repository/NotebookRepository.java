package com.github.cstroe.sqs.repository;

import com.github.cstroe.sqs.dao.NotebookDao;
import com.github.cstroe.sqs.model.Notebook;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

public class NotebookRepository implements GenericRepository<Notebook, NotebookDao> {
    @Override
    public Optional<Notebook> findById(long id) {
        return Optional.ofNullable((Notebook)getSession().createCriteria(NotebookDao.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult());
    }

    @Override
    public void create(NotebookDao p) {
        getSession().save(p);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Notebook> findAll() {
        return (List<Notebook>) getSession().createCriteria(NotebookDao.class).list();
    }
}
