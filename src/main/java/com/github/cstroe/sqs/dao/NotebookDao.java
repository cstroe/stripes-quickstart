package com.github.cstroe.sqs.dao;

import com.github.cstroe.sqs.model.Notebook;
import com.google.common.base.Preconditions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notebook")
public class NotebookDao implements Notebook {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public NotebookDao() {}

    public NotebookDao(long id, String name) {
        Preconditions.checkArgument(id >= 0);
        Preconditions.checkNotNull(name);
        this.setId(id);
        this.setName(name);
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
