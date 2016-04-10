package com.github.cstroe.sqs.repository;

import com.github.cstroe.sqs.dao.NoteDao;
import com.github.cstroe.sqs.model.Note;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

public class NoteRepository implements GenericRepository<Note, NoteDao> {
    @Override
    public Optional<Note> findById(long id) {
        return Optional.ofNullable((Note)getSession().createCriteria(NoteDao.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult());
    }

    @Override
    public void save(NoteDao p) {
        getSession().saveOrUpdate(p);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Note> findAll() {
        return (List<Note>) getSession().createCriteria(NoteDao.class).list();
    }
}
