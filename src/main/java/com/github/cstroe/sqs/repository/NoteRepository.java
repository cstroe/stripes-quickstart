package com.github.cstroe.sqs.repository;

import com.github.cstroe.sqs.dao.NoteDao;
import com.github.cstroe.sqs.model.Note;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Optional;

public class NoteRepository implements GenericRepository<Note, NoteDao> {
    @Override
    public Optional<NoteDao> findById(long id) {
        return Optional.ofNullable((NoteDao)getSession().createCriteria(NoteDao.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult());
    }

    @Override
    public void save(NoteDao p) {
        getSession().saveOrUpdate(p);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<NoteDao> findAll() {
        return (List<NoteDao>) getSession().createCriteria(NoteDao.class).list();
    }
}
