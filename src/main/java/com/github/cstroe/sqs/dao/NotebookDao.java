package com.github.cstroe.sqs.dao;

import com.github.cstroe.sqs.model.Notebook;
import com.github.cstroe.sqs.model.Note;
import com.google.common.base.Preconditions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "notebook")
public class NotebookDao implements Notebook {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    private List<Note> notes;

    private NotebookDao() {}

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

    public List<Note> getNotes() {
        return Collections.unmodifiableList(notes);
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
