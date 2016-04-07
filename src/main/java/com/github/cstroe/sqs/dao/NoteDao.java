package com.github.cstroe.sqs.dao;

import com.github.cstroe.sqs.model.Note;
import com.github.cstroe.sqs.model.Notebook;
import com.google.common.base.Preconditions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "note")
public class NoteDao implements Note {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "notebook", nullable = false)
    @ManyToOne(targetEntity = NotebookDao.class, optional = false)
    private Notebook notebook;

    public NoteDao() {}

    public NoteDao(long id, LocalDateTime created, String title, String content, Notebook notebook) {
        setId(id);
        setCreated(created);
        setTitle(title);
        setContent(content);
        setNotebook(notebook);
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        Preconditions.checkArgument(id >= 0);
        this.id = id;
    }

    @Override
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        Preconditions.checkNotNull(created);
        this.created = created;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        Preconditions.checkNotNull(title);
        this.title = title;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        Preconditions.checkNotNull(content);
        this.content = content;
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public void setNotebook(Notebook notebook) {
        Preconditions.checkNotNull(notebook);
        this.notebook = notebook;
    }
}
