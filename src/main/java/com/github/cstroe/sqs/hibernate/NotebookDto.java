package com.github.cstroe.sqs.hibernate;

import com.github.cstroe.sqs.model.Notebook;
import com.github.cstroe.sqs.model.Note;
import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.List;

public class NotebookDto implements Notebook {
    private long id;
    private String name;
    private List<Note> notes;

    private NotebookDto() {}

    public NotebookDto(long id, String name) {
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
